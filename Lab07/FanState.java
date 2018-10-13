public enum FanState {
    /*
        CO324 Lab 07
        E/14/158 gihanchanaka@gmail.com
        13-06-2018

        Refer ReadMe.pdf
    */
    OFF,MED,HI;

    FanState changeTo(FanState next){
        if(this.ordinal()==next.ordinal()){
            System.out.println("FAN: No state change.");
        }
        else{
            System.out.print("FAN: "+this+" ---");
            FanState temp=this;
            while(temp.ordinal()<next.ordinal()){
                System.out.print("(inc)");
                temp=temp.values()[temp.ordinal()+1];
            }
            while(temp.ordinal()>next.ordinal()){
                System.out.print("(dec)");
                temp=temp.values()[temp.ordinal()-1];
            }
            System.out.println("--->"+next);
        }

        return next;
    }

    FanState inc(){
        FanState next=this.values()[Math.min(this.ordinal()+1,this.values().length-1)];
        if(this==next)System.out.println("FAN: No state change.");
        else System.out.println("FAN: "+this+" ---(inc)---> "+next);
        return next;
    }
    FanState dec(){
        FanState next=this.values()[Math.max(this.ordinal()-1,0)];
        if(this==next)System.out.println("FAN: No state change.");
        else System.out.println("FAN: "+this+" ---(dec)---> "+next);
        return next;
    }

    @Override
    public String toString() {
        switch (this){
            case OFF:
                return "OFF";
            case MED:
                return "MED";
            case HI:
                return "HI";
        }
        return "NONE";
    }

}
