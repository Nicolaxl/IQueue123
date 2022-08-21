import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


public class MainScreen extends JFrame {
	Queue<Integer> numbers = new LinkedList<>();
	int now = 0;
	int counter = 0;
	int starting = 0;
	int state = 0;
	public boolean alarm = true;

	private static final long serialVersionUID = 1L;
	private JButton sendButton;
	private JTextField message;
	private JButton startServer;
	private JButton stopServer;
	private TcpServer mServer;
	JMenu menu, manual, others;
	JMenuItem i1, i2, i3;
	JMenuItem c0, c1, c2, c3, c4;
	JMenuItem entm, distm, about;


	JPanel p = new JPanel();
	JPanel q = new JPanel();
	JPanel cp = new JPanel();
	JPanel lp = new JPanel();
	JPanel rp = new JPanel();

	JLabel qnum = new JLabel(String.valueOf(now));
	JLabel cnum = new JLabel(String.valueOf(counter));
	JLabel pnum = new JLabel(String.valueOf(numbers.size()));
	JLabel tct = new JLabel("Waiting queue: 0");

	public MainScreen() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

		JFrame mainframe = new JFrame("IQueue123 Server");
		JFrame f=new JFrame("Ticketing module - IQueue123");
		JMenuBar mb = new JMenuBar();
		SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();

		menu = new JMenu("Operation");
		manual = new JMenu("Manual Override");
		others = new JMenu("Others");

		i1 = new JMenuItem("Start server services");
		i2 = new JMenuItem("Stop server services");
		i3 = new JMenuItem("Close server application");

		c0 = new JMenuItem("Trigger emergency alarm");
		c1 = new JMenuItem("Reset emergency alarm");
		c2 = new JMenuItem("Add queue manually");
		c3 = new JMenuItem("Reset queue manually");
		c4 = new JMenuItem("Call queue manually");

		entm = new JMenuItem("Enable ticketing module");
		distm = new JMenuItem("Disable ticketing module");
		about = new JMenuItem("About");

		menu.add(i1);
		menu.add(i2);
		menu.add(i3);

		manual.add(c0);
		manual.add(c1);
		manual.add(c2);
		manual.add(c3);
		manual.add(c4);

		others.add(entm);
		others.add(distm);
		others.add(about);

		i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				mServer = new TcpServer(new TcpServer.OnMessageReceived() {
					@Override

					public void messageReceived(String message) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
						System.out.println(message);
						if(message.contains("call counter")){
							int cnt = Integer.parseInt(message.substring(13));
							counter = cnt;
							System.out.println("Command received from client to call counter" + cnt);
							try{
								int call = numbers.poll();
								state = call;
								if (mServer != null) {
									mServer.sendMessage("ONC| " + call + " COUNTER NO " + counter);
									//kasi tau ke client lagi panggil no brp, disable tombol call ample call selesai
								}
								qnum.setText(String.valueOf(call));
								cnum.setText(String.valueOf(counter));
								pnum.setText(String.valueOf(numbers.size()));
								tct.setText("Waiting queue: " + numbers.size());
								audioPlayer.call(call,counter);
								if (mServer != null) {
									mServer.sendMessage("FNC|REL");
									//kasi tau ke client udh selesai manggil
								}
							}catch (NullPointerException e){
								System.out.println("No active queue!");
								if (mServer != null) {
									mServer.sendMessage("EMPTYQUEUE");
									//kasi tau ke client kalo kgk ada antrian
								}
							}
						}
						else if(message.equals("reset counter")){
							System.out.println("Command received from admin to reset queue");
							if (mServer != null) {
								mServer.sendMessage("RESET|BYADMIN");
								//kasi tau ke client klo di reset ama admin
							}
							starting=0;
							state = numbers.size();
							numbers.clear();
							qnum.setText(String.valueOf(0));
							cnum.setText(String.valueOf(0));
							pnum.setText(String.valueOf(0));
							tct.setText("Waiting queue: " + numbers.size());
						}
						else if(message.contains("backward counter")){
							if(state > 0){
								state--;
								int cnt = Integer.parseInt(message.substring(17));
								System.out.println("Command received from admin to backward queue");
								qnum.setText(String.valueOf(state));
								cnum.setText(String.valueOf(cnt));
								pnum.setText(String.valueOf(numbers.size()));
								mServer.sendMessage("RESET|BYADMIN|GRANTED|"+ state);
								tct.setText("Waiting queue: " + numbers.size());
								audioPlayer.call(state,cnt);
							}
							else{
								System.out.println("Can't backward queue because the number of virtual queue is zero");
								if (mServer != null) {
									mServer.sendMessage("RESET|BYADMIN|DENY|ZEROQUEUE");
									//kasi tau ke client klo gbs mundur soalnya 0
								}
							}
						}
						else if(message.equals("Emergency") || message.equals("this is emergency")){


							JFrame fra = new JFrame();
							JPanel frap = new JPanel();
							JDialog frd;
							frd = new JDialog(fra , "Emergency broadcast!", true);
							JLabel er = new JLabel("Emergency Alert!");
							JLabel ab = new JLabel("Please leave this place immediately!");
							er.setFont(new Font("dialog", Font.PLAIN, 75));
							ab.setFont(new Font("dialog", Font.PLAIN, 75));
							er.setForeground(Color.RED);
							ab.setForeground(Color.RED);
							frd.setLayout( new FlowLayout() );
							JButton terbut = new JButton ("Close");
							audioPlayer.clipalarm.start();
							terbut.addActionListener ( new ActionListener()
							{
								public void actionPerformed( ActionEvent e )
								{
									frd.setVisible(false);
									audioPlayer.clipalarm.stop();
								}
							});
							frap.add(er);
							frap.setLayout(new GridLayout(0, 1));
							frap.add(ab);

							frap.add(terbut);
							frd.add(frap);
							frd.setSize(1280,720);
							frd.setVisible(true);
						}
					}
				});
				mServer.start();

				i1.setEnabled(false);
				i2.setEnabled(true);

			}
		});

		i2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (mServer != null) {
						mServer.close();
				}

				// disable the stop button and enable the start one
				i1.setEnabled(true);
				i2.setEnabled(false);

			}
		});

		i3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (mServer != null) {
					mServer.close();
				}
				System.exit(0);
			}
		});

		c0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new SimpleAudioPlayer(-4);
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
					ex.printStackTrace();
				}
				JFrame fra = new JFrame();
				JPanel frap = new JPanel();
				JDialog frd;
				frd = new JDialog(fra , "Emergency broadcast!", true);
				JLabel er = new JLabel("Emergency Alert!");
				JLabel ab = new JLabel("Please leave this place immediately!");
				er.setFont(new Font("dialog", Font.PLAIN, 75));
				ab.setFont(new Font("dialog", Font.PLAIN, 75));
				er.setForeground(Color.RED);
				ab.setForeground(Color.RED);
				frd.setLayout( new FlowLayout() );
				JButton terbut = new JButton ("Close");
				audioPlayer.clipalarm.start();
				terbut.addActionListener ( new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						frd.setVisible(false);
						audioPlayer.clipalarm.stop();
					}
				});
				frap.add(er);
				frap.setLayout(new GridLayout(0, 1));
				frap.add(ab);

				frap.add(terbut);
				frd.add(frap);
				frd.setSize(1280,720);
				frd.setVisible(true);
			}
		});

		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				audioPlayer.clipalarm.stop();
			}
		});

		c2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				starting++;
				state = numbers.size();
				numbers.add(starting);
				pnum.setText(String.valueOf(numbers.size()));
				tct.setText("Waiting queue: " + numbers.size());
				System.out.println("Added queue no." + starting + ", total queue:" + numbers.size());

			}
		});

		c3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				starting=0;
				state = numbers.size();
				numbers.clear();
				qnum.setText(String.valueOf(0));
				cnum.setText(String.valueOf(0));
				pnum.setText(String.valueOf(0));
				tct.setText("Waiting queue: 0");
				System.out.println("Queue reset manually!");
				if (mServer != null) {
					mServer.sendMessage("RESET|MANUAL");
					//kasi tau ke client kalo direset manual dri server
				}
			}
		});

		c4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int call = numbers.poll();

				if (mServer != null) {
					mServer.sendMessage("ONC| " + call + "MANUAL");
					//kasi tau ke client lagi MANGGIL MANUAL, disable tombol call ample call selesai
				}
				qnum.setText(String.valueOf(call));
				cnum.setText("M/C");
				pnum.setText(String.valueOf(numbers.size()));
				tct.setText("Waiting queue: " + numbers.size());

				if (mServer != null) {
					mServer.sendMessage("FNC|REL");
					//kasi tau ke client udh selesai manggil
				}
				try{
					audioPlayer.call(call,0);
				}catch (NullPointerException f){
					System.out.println("No active queue!");
				} catch (UnsupportedAudioFileException | InterruptedException | IOException | LineUnavailableException unsupportedAudioFileException) {
					unsupportedAudioFileException.printStackTrace();
				}
			}
		});

		entm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(true);
			}
		});

		distm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
			}
		});

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				JPanel as = new JPanel();
				JDialog d;
				d = new JDialog(f , "About IQueue123", true);
				d.setLayout( new FlowLayout() );
				JButton b = new JButton ("OK");

				b.addActionListener ( new ActionListener()
				{
					public void actionPerformed( ActionEvent e )
					{
						d.setVisible(false);
					}
				});
				as.add(new JLabel ("Server for IQueue123"));
				as.add(new JLabel ("Created by Nicolas 雷國亮 D0726433"));
				as.add(new JLabel ("For Network Programming Final Project"));
				as.add(b);
				as.setLayout(new GridLayout(4,0));
				d.add(as);
				d.setSize(400,400);
				d.setVisible(true);
			}
		});

		mb.add(menu);
		mb.add(manual);
		mb.add(others);

		JLabel l = new JLabel("Ticket: ");
		JLabel l1 = new JLabel("Counter: ");
		JLabel r = new JLabel("Waiting queue: ");


		cp.add(r);
		cp.add(pnum);

		l.setFont(new Font("dialog", Font.PLAIN, 100));
		l1.setFont(new Font("dialog", Font.PLAIN, 100));
		r.setFont(new Font("dialog", Font.PLAIN, 50));

		qnum.setFont(new Font("dialog", Font.PLAIN, 200));
		cnum.setFont(new Font("dialog", Font.PLAIN, 200));
		pnum.setFont(new Font("dialog", Font.PLAIN, 50));

		tct.setFont(new Font("dialog", Font.PLAIN, 50));
		tct.setBounds(200,300, 150,200);



		JButton b=new JButton("Get numbers");
		b.setFont(new Font("dialog", Font.PLAIN, 50));
		b.setBounds(50,100,50,50);

		lp.add(l);
		lp.add(qnum);

		lp.setLayout(new GridLayout(0, 1));

		rp.add(l1);
		rp.add(cnum);

		rp.setLayout(new GridLayout(0, 1));

		l.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
		l1.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));

		qnum.setBorder(BorderFactory.createEmptyBorder(0, 0, 250, 0));
		cnum.setBorder(BorderFactory.createEmptyBorder(0, 0, 250, 0));



		JSeparator wall = new JSeparator(SwingConstants.VERTICAL);

		p.add(lp);
		p.add(rp);
		p.add(cp);

		p.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));

		p.setLayout(new GridLayout(1, 0));

		mainframe.add(p);

		f.add(tct);
		f.setLayout(new GridLayout(0, 1));
		f.add(b);

		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				starting++;
				state = numbers.size();
				numbers.add(starting);
				tct.setText("Waiting queue: " + numbers.size());
				pnum.setText(String.valueOf(numbers.size()));
				new Printer();
			}
		});

		mainframe.setJMenuBar(mb);
		mainframe.setSize(1280, 768);
		mainframe.setVisible(true);

		f.setSize(576,576);
		f.setVisible(true);

	}
}
