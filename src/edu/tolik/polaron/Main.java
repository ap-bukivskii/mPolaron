package edu.tolik.polaron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by tolik_b on 12/18/14.
 */
public class Main {
    public static void main(String[] args) {
        double W0;
        double Ta;
        Scanner input = new Scanner(System.in);
        System.out.print("W0 = ");
        W0 = input.nextDouble();
        W0/=1000.;
        System.out.print("Ta = ");
        Ta = input.nextDouble();
        input.close();
        Polaron p = new Polaron(W0,Ta);
        String path = "polaron/W0_" + W0*1000 + "_meV___Ta_" + Ta + "_K.txt";
        PrintWriter writer = null;
        try {writer = new PrintWriter(path, "UTF-8");} catch (Exception e) {e.printStackTrace(); System.exit(0);}
        writer.println("T\tEp\t\\g(D)_exc/2\t\\g(D)_fluct/2\t\\g(D)_sum/2");
        for (double t=0.1;t<300.;t+=0.1){writer.println(t+"\t"+p.getEp(t)+"\t"+(p.getM(t)/2.)+"\t"+(p.getF(t)/2.)+"\t"+(p.getD(t)/2.));}
        writer.close();
        System.out.println("Data has been written to \""+path+"\"");
    }
}
