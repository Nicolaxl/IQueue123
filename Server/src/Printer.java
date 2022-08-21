import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.print.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Printer implements Printable, ActionListener{
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        MainScreen mainScreen = null;
        try {
            mainScreen = new MainScreen();
        } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }
        graphics.drawString("國亮銀行 KuoLiang Bank", 100, 100);
        graphics.drawString("Customer service queue", 100, 125);
        assert mainScreen != null;
        graphics.drawString("Your ticket number: " + mainScreen.starting, 100, 150);
        graphics.drawString("Waiting queue: " + mainScreen.numbers.size(), 100, 175);
        graphics.drawString("Thank you for using Kuoliang Bank.", 100, 225);
        graphics.drawString(dtf.format(now), 100, 250);
            return PAGE_EXISTS;
    }


    public void actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
            }
        }
    }
}
