package edu.tolik.polaron;

/**
 * Created by tolik_b on 12/18/14.
 */
public class Polaron {
    public      static  final   double  KB      =   8.6173324787879e-5;
    public      static  final   double  sqrtPI  =   Math.sqrt(Math.PI);
    protected                   double  Ep0;
    protected                   double  T0;
    protected                   double  W0;
    protected                   double  Ta;
    protected                   double  lastD;
    protected                   double  lastM;
    protected                   double  lastF;
    protected                   double  lastTM;
    protected                   double  lastTF;
    protected                   double  lastTD;

    
    Polaron(double W0, double Ta, double Ep0, double T0)    {
        this.Ep0 = Ep0; this.T0 = T0; this.W0 = W0; this.Ta = Ta;}
    Polaron(double W0, double Ta)                           {
        this.W0 = W0; this.Ta = Ta;}

    public    double    getM    (double T)                  {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T==this.lastTM){return this.lastM;}
        else{
            this.lastM   =  6.0*(1.0+this.getEp(T)/3.0/this.KB/T)/(1.0+this.getEp(T)/this.KB/T)*this.getEp(T)*ErrorFunction.erf(Math.sqrt(this.getEp(T)/2.0/this.KB/T));
            this.lastTM  =  T;
            return this.lastM;
        }
    }
    public    double    getF    (double T)                  {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T==this.lastTF){return this.lastF;}
        else{
            this.lastF   =   2.0/this.sqrtPI*(1.0+this.getEp(T)/2.0/this.KB/T)/(1+this.getEp(T)/this.KB/T)*(Math.sqrt(8.0*this.getEp(T)*this.KB*T)*Math.exp(-this.getEp(T)/2.0/this.KB/T));
            this.lastTF  =   T;
            return this.lastF;
        }
    }
    public    double    getD    (double T)                  {
        if  (T<=0){throw new IllegalArgumentException("Absolute temperature can not be lower or equal zero!");}
        if  (T== this.lastTD){return this.lastD;}
        else {
            this.lastD = this.getF(T) + this.getM(T);
            this.lastTD = T;
            return this.lastD;
        }
    }
    protected double    getDexp (double T)                  {
        return this.Ep0*Math.exp(-T/T0);}
    public double       getEp   (double T)                  {
        return this.W0*this.W0/4/this.KB/(T+this.Ta);}

    //protected double    getW0   (double T)                {
    //    return Math.sqrt(this.getD_2(T)*4*this.KB*T);} 

}
