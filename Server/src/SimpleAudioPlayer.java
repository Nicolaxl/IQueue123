import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SimpleAudioPlayer
{
    private static final int DELAY = 100;

    Clip qnumnow, qnumcounter, clip1en, clip2en, clip3en, clip4en, clip5en, clip6en, clip7en, clip8en, clip9en,
            clip10en, clip11en, clip12en, clip13en, clip14en, clip15en, clip16en, clip17en, clip18en, clip19en,
            clip20en, clip30en, clip40en, clip50en, clip60en, clip70en, clip80en, clip90en, clip100en, clipthousanden,
            clipchime, clipalarm;

    AudioInputStream audioInputStream;
    static String qnumfile_en = "Component/english/queuenumber_en.wav", qnumcounter_en = "Component/english/counternumber_en.wav",
            qnum1_en = "Component/english/1_en.wav",
            qnum2_en = "Component/english/2_en.wav", qnum3_en = "Component/english/3_en.wav",
            qnum4_en = "Component/english/4_en.wav", qnum5_en = "Component/english/5_en.wav",
            qnum6_en = "Component/english/6_en.wav", qnum7_en = "Component/english/7_en.wav",
            qnum8_en = "Component/english/8_en.wav", qnum9_en = "Component/english/9_en.wav",
            qnum10_en = "Component/english/10_en.wav", qnum11_en = "Component/english/11_en.wav",
            qnum12_en = "Component/english/12_en.wav", qnum13_en = "Component/english/13_en.wav",
            qnum14_en = "Component/english/14_en.wav", qnum15_en = "Component/english/15_en.wav",
            qnum16_en = "Component/english/16_en.wav", qnum17_en = "Component/english/17_en.wav",
            qnum18_en = "Component/english/18_en.wav", qnum19_en = "Component/english/19_en.wav",
            qnum20_en = "Component/english/20_en.wav", qnum30_en = "Component/english/30_en.wav",
            qnum40_en = "Component/english/40_en.wav", qnum50_en = "Component/english/50_en.wav",
            qnum60_en = "Component/english/60_en.wav", qnum70_en = "Component/english/70_en.wav",
            qnum80_en = "Component/english/80_en.wav", qnum90_en = "Component/english/90_en.wav",
            qnum100_en = "Component/english/100_en.wav", qnumthousand_en = "Component/english/thousand_en.wav",
            qnumchime = "Component/english/qchime.wav", epalarm = "Component/english/poundalarm.wav";




    public SimpleAudioPlayer(int sel) throws UnsupportedAudioFileException, IOException, LineUnavailableException {


        switch(sel) {
            case -2:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnumcounter_en).getAbsoluteFile());
                qnumcounter = AudioSystem.getClip();
                qnumcounter.open(audioInputStream);
                qnumcounter.start();
                while(qnumcounter.getMicrosecondLength() != qnumcounter.getMicrosecondPosition()) {}
                break;
            case -1:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnumfile_en).getAbsoluteFile());
                qnumnow = AudioSystem.getClip();
                qnumnow.open(audioInputStream);
                qnumnow.start();
                while(qnumnow.getMicrosecondLength() != qnumnow.getMicrosecondPosition()) {}
                break;
            case -3:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnumchime).getAbsoluteFile());
                clipchime = AudioSystem.getClip();
                clipchime.open(audioInputStream);
                clipchime.start();
                while(clipchime.getMicrosecondLength() != clipchime.getMicrosecondPosition()) {}
                break;
            /*case -4:
                clipalarm.start();
                System.out.println("Alarm responded!");
                //while(clipalarm.getMicrosecondLength() != clipalarm.getMicrosecondPosition()) {}
                break;
            case -5:
                clipalarm.stop();
                break;*/
            case 1:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum1_en).getAbsoluteFile());
                clip1en = AudioSystem.getClip();
                clip1en.open(audioInputStream);
                clip1en.start();
                while(clip1en.getMicrosecondLength() != clip1en.getMicrosecondPosition()) {}
                break;
            case 2:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum2_en).getAbsoluteFile());
                clip2en = AudioSystem.getClip();
                clip2en.open(audioInputStream);
                clip2en.start();
                while(clip2en.getMicrosecondLength() != clip2en.getMicrosecondPosition()) {}
                break;
            case 3:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum3_en).getAbsoluteFile());
                clip3en = AudioSystem.getClip();
                clip3en.open(audioInputStream);
                clip3en.start();
                while(clip3en.getMicrosecondLength() != clip3en.getMicrosecondPosition()) {}
                break;
            case 4:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum4_en).getAbsoluteFile());
                clip4en = AudioSystem.getClip();
                clip4en.open(audioInputStream);
                clip4en.start();
                while(clip4en.getMicrosecondLength() != clip4en.getMicrosecondPosition()) {}
                break;
            case 5:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum5_en).getAbsoluteFile());
                clip5en = AudioSystem.getClip();
                clip5en.open(audioInputStream);
                clip5en.start();
                while(clip5en.getMicrosecondLength() != clip5en.getMicrosecondPosition()) {}
                break;
            case 6:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum6_en).getAbsoluteFile());
                clip6en = AudioSystem.getClip();
                clip6en.open(audioInputStream);
                clip6en.start();
                while(clip6en.getMicrosecondLength() != clip6en.getMicrosecondPosition()) {}
                break;
            case 7:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum7_en).getAbsoluteFile());
                clip7en = AudioSystem.getClip();
                clip7en.open(audioInputStream);
                clip7en.start();
                while(clip7en.getMicrosecondLength() != clip7en.getMicrosecondPosition()) {}
                break;
            case 8:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum8_en).getAbsoluteFile());
                clip8en = AudioSystem.getClip();
                clip8en.open(audioInputStream);
                clip8en.start();
                while(clip8en.getMicrosecondLength() != clip8en.getMicrosecondPosition()) {}
                break;
            case 9:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum9_en).getAbsoluteFile());
                clip9en = AudioSystem.getClip();
                clip9en.open(audioInputStream);
                clip9en.start();
                while(clip9en.getMicrosecondLength() != clip9en.getMicrosecondPosition()) {}
                break;
            case 10:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum10_en).getAbsoluteFile());
                clip10en = AudioSystem.getClip();
                clip10en.open(audioInputStream);
                clip10en.start();
                while(clip10en.getMicrosecondLength() != clip10en.getMicrosecondPosition()) {}
                break;
            case 11:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum11_en).getAbsoluteFile());
                clip11en = AudioSystem.getClip();
                clip11en.open(audioInputStream);
                clip11en.start();
                while(clip11en.getMicrosecondLength() != clip11en.getMicrosecondPosition()) {}
                break;
            case 12:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum12_en).getAbsoluteFile());
                clip12en = AudioSystem.getClip();
                clip12en.open(audioInputStream);
                clip12en.start();
                while(clip12en.getMicrosecondLength() != clip12en.getMicrosecondPosition()) {}
                break;
            case 13:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum13_en).getAbsoluteFile());
                clip13en = AudioSystem.getClip();
                clip13en.open(audioInputStream);
                clip13en.start();
                while(clip13en.getMicrosecondLength() != clip13en.getMicrosecondPosition()) {}
                break;
            case 14:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum14_en).getAbsoluteFile());
                clip14en = AudioSystem.getClip();
                clip14en.open(audioInputStream);
                clip14en.start();
                while(clip14en.getMicrosecondLength() != clip14en.getMicrosecondPosition()) {}
                break;
            case 15:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum15_en).getAbsoluteFile());
                clip15en = AudioSystem.getClip();
                clip15en.open(audioInputStream);
                clip15en.start();
                while(clip15en.getMicrosecondLength() != clip15en.getMicrosecondPosition()) {}
                break;
            case 16:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum16_en).getAbsoluteFile());
                clip16en = AudioSystem.getClip();
                clip16en.open(audioInputStream);
                clip16en.start();
                while(clip16en.getMicrosecondLength() != clip16en.getMicrosecondPosition()) {}
                break;
            case 17:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum17_en).getAbsoluteFile());
                clip17en = AudioSystem.getClip();
                clip17en.open(audioInputStream);
                clip17en.start();
                while(clip17en.getMicrosecondLength() != clip17en.getMicrosecondPosition()) {}
                break;
            case 18:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum18_en).getAbsoluteFile());
                clip18en = AudioSystem.getClip();
                clip18en.open(audioInputStream);
                clip18en.start();
                while(clip18en.getMicrosecondLength() != clip18en.getMicrosecondPosition()) {}
                break;
            case 19:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum19_en).getAbsoluteFile());
                clip19en = AudioSystem.getClip();
                clip19en.open(audioInputStream);
                clip19en.start();
                while(clip19en.getMicrosecondLength() != clip19en.getMicrosecondPosition()) {}
                break;
            case 20:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum20_en).getAbsoluteFile());
                clip20en = AudioSystem.getClip();
                clip20en.open(audioInputStream);
                clip20en.start();
                while(clip20en.getMicrosecondLength() != clip20en.getMicrosecondPosition()) {}
                break;
            case 30:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum30_en).getAbsoluteFile());
                clip30en = AudioSystem.getClip();
                clip30en.open(audioInputStream);
                clip30en.start();
                while(clip30en.getMicrosecondLength() != clip30en.getMicrosecondPosition()) {}
                break;
            case 40:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum40_en).getAbsoluteFile());
                clip40en = AudioSystem.getClip();
                clip40en.open(audioInputStream);
                clip40en.start();
                while(clip40en.getMicrosecondLength() != clip40en.getMicrosecondPosition()) {}
                break;
            case 50:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum50_en).getAbsoluteFile());
                clip50en = AudioSystem.getClip();
                clip50en.open(audioInputStream);
                clip50en.start();
                while(clip50en.getMicrosecondLength() != clip50en.getMicrosecondPosition()) {}
                break;
            case 60:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum60_en).getAbsoluteFile());
                clip60en = AudioSystem.getClip();
                clip60en.open(audioInputStream);
                clip60en.start();
                while(clip60en.getMicrosecondLength() != clip60en.getMicrosecondPosition()) {}
                break;
            case 70:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum70_en).getAbsoluteFile());
                clip70en = AudioSystem.getClip();
                clip70en.open(audioInputStream);
                clip70en.start();
                while(clip70en.getMicrosecondLength() != clip70en.getMicrosecondPosition()) {}
                break;
            case 80:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum80_en).getAbsoluteFile());
                clip80en = AudioSystem.getClip();
                clip80en.open(audioInputStream);
                clip80en.start();
                while(clip80en.getMicrosecondLength() != clip80en.getMicrosecondPosition()) {}
                break;
            case 90:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum90_en).getAbsoluteFile());
                clip90en = AudioSystem.getClip();
                clip90en.open(audioInputStream);
                clip90en.start();
                while(clip90en.getMicrosecondLength() != clip90en.getMicrosecondPosition()) {}
                break;
            case 100:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnum100_en).getAbsoluteFile());
                clip100en = AudioSystem.getClip();
                clip100en.open(audioInputStream);
                clip100en.start();
                while(clip100en.getMicrosecondLength() != clip100en.getMicrosecondPosition()) {}
                break;
            case 1000:
                audioInputStream = AudioSystem.getAudioInputStream(new File(qnumthousand_en).getAbsoluteFile());
                clipthousanden = AudioSystem.getClip();
                clipthousanden.open(audioInputStream);
                clipthousanden.start();
                while(clipthousanden.getMicrosecondLength() != clipthousanden.getMicrosecondPosition()) {}
                break;
            default:
                // code block
        }
    }

    public SimpleAudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(epalarm).getAbsoluteFile());
        clipalarm = AudioSystem.getClip();
        clipalarm.open(audioInputStream);
    }

    public void emergencyactive(){

    }

    public void call(int which, int counter) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
            try {
                // which + counter
                new SimpleAudioPlayer(-3);
                TimeUnit.MILLISECONDS.sleep(DELAY);
                new SimpleAudioPlayer(-1);
                TimeUnit.MILLISECONDS.sleep(DELAY);

                int temp = 0;
                if(which >= 11 && which <=19){
                            new SimpleAudioPlayer(which);
                            TimeUnit.MILLISECONDS.sleep(DELAY);
                }
                else{
                    temp = (int) which / 1000;
                    if(temp != 0){
                        new SimpleAudioPlayer(temp);
                        TimeUnit.MILLISECONDS.sleep(DELAY);
                        new SimpleAudioPlayer(1000);
                        TimeUnit.MILLISECONDS.sleep(DELAY);
                    }

                    which = which - (temp * 1000);
                    temp = (int) which / 100;

                    if(temp != 0){
                        new SimpleAudioPlayer(temp);
                        TimeUnit.MILLISECONDS.sleep(DELAY);
                        new SimpleAudioPlayer(100);
                        TimeUnit.MILLISECONDS.sleep(DELAY);
                    }

                    which = which - (temp * 100);
                    temp = (int) which / 10;

                    if(which >= 11 && which <= 19){
                        new SimpleAudioPlayer(which);
                        TimeUnit.MILLISECONDS.sleep(DELAY);
                    }
                    else {
                        if(temp != 0){
                            new SimpleAudioPlayer(temp * 10);
                            TimeUnit.MILLISECONDS.sleep(DELAY);
                        }

                        which = which - (temp * 10);
                        temp = (int) which / 1;

                        if(temp != 0){
                            new SimpleAudioPlayer(temp);
                            TimeUnit.MILLISECONDS.sleep(DELAY);
                        }
                    }
                }
                new SimpleAudioPlayer(-2);
                TimeUnit.MILLISECONDS.sleep(DELAY);
                new SimpleAudioPlayer(counter);
                TimeUnit.MILLISECONDS.sleep(DELAY);

            }catch (NoSuchElementException e){
                System.out.println("EOF Exit!");
                System.exit(0);
            }
        }
    }
