package com.yufengandbabaozhou.werewolf.Identity;

public class NumberMan {
    int Wolf = 1 ;
    int Witch = 0;
    int Hunter = 0;

    public  int getWolfnumber() {
            return this.Wolf;
    }
    public  int addWolfnumber() {
        this.Wolf = Wolf +1;
        return Wolf;
    }
    public  int downWolfnumber() {
        if(Wolf ==1){
            return 0;
        }else {
            this.Wolf = Wolf -1;
            return Wolf;
        }

    }

    public  int getwitchnumber() {
            return this.Witch;
    }

    public  int addwitchnumber() {
        this.Witch = Witch +1;
        return Witch;
    }
    public  int downwitchnumber() {
        if(Witch ==0){

            return 0;
        }else {
            this.Witch = Witch -1;
            return Witch;
        }

    }



    public  int gethunternumber() {

        return this.Hunter;
    }
    public int addhunternumber() {
        this.Hunter = Hunter +1;
        return Hunter;
    }
    public  int downhunternumber() {
        if(Hunter ==0){
            return 0;
        }else  {
            this.Hunter = Hunter -1;
            return Hunter;
        }
    }
}
