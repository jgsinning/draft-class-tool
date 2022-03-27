public class Player {

    public String firstName;
    public String lastName;
    public String position;
    public int projection;
    public int height;
    public int weight;
    public int age;
    public double m16ovr;
    public String dev;

    public int acc;
    public int agi;
    public int awr;
    public int bcv;
    public int bsh;
    public int bsk;
    public int btk;
    public int car;
    public int cth;
    public int cit;
    public int cod;
    public int fmv;
    public int pow;
    public int ibl;
    public int inj;
    public int jkm;
    public int jmp;
    public int kac;
    public int kpw;
    public int ret;
    public int lbk;
    public int mcv;
    public int pbf;
    public int pbp;
    public int pbk;
    public int per;
    public int pac;
    public int prc;
    public int pmv;
    public int prs;
    public int pur;
    public int rls;
    public int drr;
    public int mrr;
    public int srr;
    public int rbf;
    public int rbp;
    public int rbk;
    public int unknown1;
    public int spc;
    public int spd;
    public int spm;
    public int sta;
    public int sfa;
    public int str;
    public int tak;
    public int dac;
    public int mac;
    public int unknown2;
    public int sac;
    public int run;
    public int thp;
    public int tup;
    public int tgh;
    public int trk;
    public int zcv;

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Player(String[] row) {
        this.firstName = row[0];
        this.lastName = row[1];
        setPosition(row[2]);
        int[] ahw = {Integer.parseInt(row[6]), Integer.parseInt(row[4]), Integer.parseInt(row[5])};
        setAgeHtWt(ahw);
        setDev(row[8]);
        updateAttributes(row);
    }

    public String toString() {
        String retStr = this.firstName + " " + this.lastName;
        return retStr;
    }

    public String attrsStr() {
        String retStr = this.acc + " " + this.agi + " " + this.awr;
        return retStr;
    }

    public void setPosition(int pos) {
        switch (pos) {
            case 0: this.position = "QB"; break;
            case 1: this.position = "HB"; break;
            case 2: this.position = "FB"; break;
            case 3: this.position = "WR"; break;
            case 4: this.position = "TE"; break;
            case 5: this.position = "LT"; break;
            case 6: this.position = "LG"; break;
            case 7: this.position = "C"; break;
            case 8: this.position = "RG"; break;
            case 9: this.position = "RT"; break;
            case 10: this.position = "LE"; break;
            case 11: this.position = "RE"; break;
            case 12: this.position = "DT"; break;
            case 13: this.position = "LOLB"; break;
            case 14: this.position = "MLB"; break;
            case 15: this.position = "ROLB"; break;
            case 16: this.position = "CB"; break;
            case 17: this.position = "FS"; break;
            case 18: this.position = "SS"; break;
            case 19: this.position = "K"; break;
            case 20: this.position = "P"; break;
            default: this.position = "NA"; break;
        }
    }

    public void setPosition(String pos) {
        switch(pos) {
            case "QB": this.position = "0"; break;
            case "HB": this.position = "1"; break;
            case "FB": this.position = "2"; break;
            case "WR": this.position = "3"; break;
            case "TE": this.position = "4"; break;
            case "LT": this.position = "5"; break;
            case "LG": this.position = "6"; break;
            case "C": this.position = "7"; break;
            case "RG": this.position = "8"; break;
            case "RT": this.position = "9"; break;
            case "LE": this.position = "10"; break;
            case "RE": this.position = "11"; break;
            case "DT": this.position = "12"; break;
            case "LOLB": this.position = "13"; break;
            case "MLB": this.position = "14"; break;
            case "ROLB": this.position = "15"; break;
            case "CB": this.position = "16"; break;
            case "FS": this.position = "17"; break;
            case "SS": this.position = "18"; break;
            case "K": this.position = "19"; break;
            default: this.position = "20"; break;
        }
    }

    public void setProjection(int[] projection) {
        this.projection = (projection[1] - 1) * 32 + projection[0];
    }

    public void setAgeHtWt(int[] arr) {
        this.age = arr[0];
        this.height = arr[1];
        if (arr[2] >= 0) {
            this.weight = arr[2] + 160;
        } else {
            this.weight = 256 + arr[2] + 160;
        }
    }

    public void setDev(int d) {
        switch (d) {
            case 0: this.dev = "NORMAL"; break;
            case 1: this.dev = "STAR"; break;
            case 2: this.dev = "SUPERSTAR"; break;
            case 3: this.dev = "XFACTOR"; break;
            default: this.dev = "NA"; break;
        }
    }

    public void setDev(String d) {
        switch (d) {
            case "XFACTOR": this.dev = "3"; break;
            case "SUPERSTAR": this.dev = "2"; break;
            case "STAR": this.dev = "1"; break;
            default: this.dev = "0"; break;
        }
    }

    public void updateOvr() {
        // https://imgur.com/R8BUtXT
        switch (this.position) {
            case "QB": this.m16ovr = this.awr * 0.2 + this.thp * 0.2 + this.sac * 0.15 + this.mac * 0.15 + this.dac * 0.1 + this.pac * 0.08 + this.spd * 0.05 + this.agi * 0.03 + this.run * 0.03 + this.acc * 0.01; break;
            case "HB": this.m16ovr = this.awr * 0.13 + this.str * 0.02 + this.spd * 0.13 + this.acc * 0.08 + this.agi * 0.08 + this.car * 0.13 + this.cth * 0.02 + this.srr * 0.01 + this.bcv * 0.13 + this.trk * 0.08 + this.cod * 0.08 + this.sfa * 0.02 + this.spm * 0.04 + this.jkm * 0.04; break;
            case "FB": this.m16ovr = this.pbk * 0.09 + this.awr * 0.18 + this.rbk * 0.18 + this.str * 0.03 + this.ibl * 0.12 + this.spd * 0.06 + this.acc * 0.06 + this.agi * 0.03 + this.car * 0.09 + this.cth * 0.03 + this.bcv * 0.03 + this.trk * 0.06 + this.cod * 0.02 + this.sfa * 0.03; break;
            case "WR": this.m16ovr = this.awr * 0.12 + this.str * 0.02 + this.spd * 0.12 + this.acc * 0.08 + this.agi * 0.06 + this.car * 0.04 + this.cth * 0.12 + this.srr * 0.04 + this.mrr * 0.04 + this.drr * 0.04 + this.bcv * 0.02 + this.trk * 0.01 + this.cod * 0.04 + this.cit * 0.08 + this.rls * 0.08 + this.spc * 0.04 + this.sfa * 0.01 + this.spm * 0.01 + this.jkm * 0.01 + this.jmp * 0.04; break;
            case "TE": this.m16ovr = this.pbk * 0.04 + this.awr * 0.12 + this.rbk * 0.12 + this.str * 0.06 + this.ibl * 0.06 + this.spd * 0.06 + this.acc * 0.04 + this.agi * 0.04 + this.car * 0.02 + this.cth * 0.08 + this.srr * 0.03 + this.mrr * 0.03 + this.drr * 0.02 + this.bcv * 0.02 + this.trk * 0.02 + this.cod * 0.02 + this.cit * 0.08 + this.rls * 0.02 + this.spc * 0.04 + this.sfa * 0.01 + this.spm * 0.01 + this.jkm * 0.01 + this.jmp * 0.04; break;
            case "LT": this.m16ovr = this.pbk * 0.39 + this.awr * 0.17 + this.rbk * 0.19 + this.str * 0.11 + this.ibl * 0.06 + this.spd * 0.03 + this.acc * 0.03 + this.agi * 0.03; break;
            case "LG": this.m16ovr = this.pbk * 0.29 + this.awr * 0.18 + this.rbk * 0.18 + this.str * 0.12 + this.ibl * 0.09 + this.spd * 0.03 + this.acc * 0.06 + this.agi * 0.06; break;
            case "C": this.m16ovr = this.pbk * 0.27 + this.awr * 0.2 + this.rbk * 0.27 + this.str * 0.1 + this.ibl * 0.07 + this.spd * 0.03 + this.acc * 0.03 + this.agi * 0.03; break;
            case "RG": this.m16ovr = this.pbk * 0.29 + this.awr * 0.18 + this.rbk * 0.18 + this.str * 0.12 + this.ibl * 0.09 + this.spd * 0.03 + this.acc * 0.06 + this.agi * 0.06; break;
            case "RT": this.m16ovr = this.pbk * 0.2 + this.awr * 0.2 + this.rbk * 0.33 + this.str * 0.1 + this.ibl * 0.07 + this.spd * 0.03 + this.acc * 0.03 + this.agi * 0.03; break;
            case "LE": this.m16ovr = this.awr * 0.14 + this.tak * 0.1 + this.prc * 0.07 + this.spd * 0.1 + this.acc * 0.1 + this.bsh * 0.1 + this.pmv * 0.12 + this.fmv * 0.12 + this.str * 0.05 + this.pur * 0.05 + this.agi * 0.05 + this.pow * 0.01; break;
            case "RE": this.m16ovr = this.awr * 0.14 + this.tak * 0.1 + this.prc * 0.07 + this.spd * 0.1 + this.acc * 0.1 + this.bsh * 0.1 + this.pmv * 0.12 + this.fmv * 0.12 + this.str * 0.05 + this.pur * 0.05 + this.agi * 0.05 + this.pow * 0.01; break;
            case "DT": this.m16ovr = this.awr * 0.17 + this.tak * 0.09 + this.prc * 0.06 + this.spd * 0.06 + this.acc * 0.06 + this.bsh * 0.11 + this.pmv * 0.11 + this.fmv * 0.11 + this.str * 0.17 + this.pur * 0.03 + this.agi * 0.03; break;
            case "LOLB": this.m16ovr = this.awr * 0.17 + this.tak * 0.1 + this.prc * 0.1 + this.spd * 0.08 + this.acc * 0.05 + this.bsh * 0.08 + this.pmv * 0.08 + this.fmv * 0.08 + this.str * 0.05 + this.zcv * 0.05 + this.pur * 0.05 + this.mcv * 0.04 + this.agi * 0.03 + this.pow * 0.03 + this.cth * 0.02; break;
            case "ROLB": this.m16ovr = this.awr * 0.17 + this.tak * 0.1 + this.prc * 0.1 + this.spd * 0.08 + this.acc * 0.05 + this.bsh * 0.08 + this.pmv * 0.08 + this.fmv * 0.08 + this.str * 0.05 + this.zcv * 0.05 + this.pur * 0.05 + this.mcv * 0.04 + this.agi * 0.03 + this.pow * 0.03 + this.cth * 0.02; break;
            case "MLB": this.m16ovr = this.awr * 0.13 + this.tak * 0.17 + this.prc * 0.13 + this.spd * 0.06 + this.acc * 0.04 + this.bsh * 0.13 + this.pmv * 0.03 + this.fmv * 0.03 + this.str * 0.04 + this.zcv * 0.03 + this.pur * 0.09 + this.mcv * 0.02 + this.agi * 0.04 + this.pow * 0.04; break;
            case "CB": this.m16ovr = this.awr * 0.14 + this.tak * 0.05 + this.prc * 0.09 + this.spd * 0.14 + this.acc * 0.14 + this.str * 0.02 + this.zcv * 0.09 + this.mcv * 0.19 + this.agi * 0.05 + this.jmp * 0.05 + this.prs * 0.05; break;
            case "SS": this.m16ovr = this.awr * 0.18 + this.tak * 0.12 + this.prc * 0.12 + this.spd * 0.09 + this.acc * 0.03 + this.str * 0.06 + this.zcv * 0.12 + this.pur * 0.09 + this.mcv * 0.06 + this.agi * 0.03 + this.pow * 0.06 + this.jmp * 0.03 + this.cth * 0.03; break;
            case "FS": this.m16ovr = this.awr * 0.16 + this.tak * 0.11 + this.prc * 0.11 + this.spd * 0.11 + this.acc * 0.05 + this.str * 0.03 + this.zcv * 0.16 + this.pur * 0.05 + this.mcv * 0.05 + this.agi * 0.05 + this.pow * 0.03 + this.jmp * 0.05 + this.cth * 0.02; break;
            case "K": this.m16ovr = this.kac * 0.45 + this.awr * 0.27 + this.kpw * 0.27; break;
            case "P": this.m16ovr = this.kac * 0.4 + this.awr * 0.3 + this.kpw * 0.3; break;
            default: this.m16ovr = 0.0; break;
        }
    }

    public String[] csvLine() {
        String[] line = {
                this.firstName,
                this.lastName,
                this.position,
                Integer.toString(this.projection),
                Integer.toString(this.height),
                Integer.toString(this.weight),
                Integer.toString(this.age),
                Double.toString(this.m16ovr),
                this.dev,
                Integer.toString(this.awr),
                Integer.toString(this.spd),
                Integer.toString(this.acc),
                Integer.toString(this.agi),
                Integer.toString(this.str),
                Integer.toString(this.car),
                Integer.toString(this.bcv),
                Integer.toString(this.cod),
                Integer.toString(this.jkm),
                Integer.toString(this.spm),
                Integer.toString(this.sfa),
                Integer.toString(this.trk),
                Integer.toString(this.btk),
                Integer.toString(this.cth),
                Integer.toString(this.cit),
                Integer.toString(this.spc),
                Integer.toString(this.srr),
                Integer.toString(this.mrr),
                Integer.toString(this.drr),
                Integer.toString(this.rls),
                Integer.toString(this.jmp),
                Integer.toString(this.thp),
                Integer.toString(this.sac),
                Integer.toString(this.mac),
                Integer.toString(this.dac),
                Integer.toString(this.run),
                Integer.toString(this.pac),
                Integer.toString(this.tup),
                Integer.toString(this.bsk),
                Integer.toString(this.pbk),
                Integer.toString(this.pbf),
                Integer.toString(this.pbp),
                Integer.toString(this.rbk),
                Integer.toString(this.rbf),
                Integer.toString(this.rbp),
                Integer.toString(this.ibl),
                Integer.toString(this.lbk),
                Integer.toString(this.prc),
                Integer.toString(this.tak),
                Integer.toString(this.pow),
                Integer.toString(this.bsh),
                Integer.toString(this.fmv),
                Integer.toString(this.pmv),
                Integer.toString(this.pur),
                Integer.toString(this.mcv),
                Integer.toString(this.zcv),
                Integer.toString(this.prs),
                Integer.toString(this.ret),
                Integer.toString(this.kpw),
                Integer.toString(this.kac),
                Integer.toString(this.sta),
                Integer.toString(this.tgh),
                Integer.toString(this.inj),
                Integer.toString(this.per),
                Integer.toString(this.unknown1),
                Integer.toString(this.unknown2)
        };
        return line;
    }

    public void updateAttributes(int[] attrs) {
        this.acc = attrs[0];
        this.agi = attrs[1];
        this.awr = attrs[2];
        this.bcv = attrs[3];
        this.bsh = attrs[4];
        this.bsk = attrs[5];
        this.btk = attrs[6];
        this.car = attrs[7];
        this.cth = attrs[8];
        this.cit = attrs[9];
        this.cod = attrs[10];
        this.fmv = attrs[11];
        this.pow = attrs[12];
        this.ibl = attrs[13];
        this.inj = attrs[14];
        this.jkm = attrs[15];
        this.jmp = attrs[16];
        this.kac = attrs[17];
        this.kpw = attrs[18];
        this.ret = attrs[19];
        this.lbk = attrs[20];
        this.mcv = attrs[21];
        this.pbf = attrs[22];
        this.pbp = attrs[23];
        this.pbk = attrs[24];
        this.per = attrs[25];
        this.pac = attrs[26];
        this.prc = attrs[27];
        this.pmv = attrs[28];
        this.prs = attrs[29];
        this.pur = attrs[30];
        this.rls = attrs[31];
        this.drr = attrs[32];
        this.mrr = attrs[33];
        this.srr = attrs[34];
        this.rbf = attrs[35];
        this.rbp = attrs[36];
        this.rbk = attrs[37];
        this.unknown1 = attrs[38];
        this.spc = attrs[39];
        this.spd = attrs[40];
        this.spm = attrs[41];
        this.sta = attrs[42];
        this.sfa = attrs[43];
        this.str = attrs[44];
        this.tak = attrs[45];
        this.dac = attrs[46];
        this.mac = attrs[47];
        this.unknown2 = attrs[48];
        this.sac = attrs[49];
        this.run = attrs[50];
        this.thp = attrs[51];
        this.tup = attrs[52];
        this.tgh = attrs[53];
        this.trk = attrs[54];
        this.zcv = attrs[55];
    }

    public void updateAttributes(String[] row) {
        this.awr = Integer.parseInt(row[9]);
        this.spd = Integer.parseInt(row[10]);
        this.acc = Integer.parseInt(row[11]);
        this.agi = Integer.parseInt(row[12]);
        this.str = Integer.parseInt(row[13]);
        this.car = Integer.parseInt(row[14]);
        this.bcv = Integer.parseInt(row[15]);
        this.cod = Integer.parseInt(row[16]);
        this.jkm = Integer.parseInt(row[17]);
        this.spm = Integer.parseInt(row[18]);
        this.sfa = Integer.parseInt(row[19]);
        this.trk = Integer.parseInt(row[20]);
        this.btk = Integer.parseInt(row[21]);
        this.cth = Integer.parseInt(row[22]);
        this.cit = Integer.parseInt(row[23]);
        this.spc = Integer.parseInt(row[24]);
        this.srr = Integer.parseInt(row[25]);
        this.mrr = Integer.parseInt(row[26]);
        this.drr = Integer.parseInt(row[27]);
        this.rls = Integer.parseInt(row[28]);
        this.jmp = Integer.parseInt(row[29]);
        this.thp = Integer.parseInt(row[30]);
        this.sac = Integer.parseInt(row[31]);
        this.mac = Integer.parseInt(row[32]);
        this.dac = Integer.parseInt(row[33]);
        this.run = Integer.parseInt(row[34]);
        this.pac = Integer.parseInt(row[35]);
        this.tup = Integer.parseInt(row[36]);
        this.bsk = Integer.parseInt(row[37]);
        this.pbk = Integer.parseInt(row[38]);
        this.pbf = Integer.parseInt(row[39]);
        this.pbp = Integer.parseInt(row[40]);
        this.rbk = Integer.parseInt(row[41]);
        this.rbf = Integer.parseInt(row[42]);
        this.rbp = Integer.parseInt(row[43]);
        this.ibl = Integer.parseInt(row[44]);
        this.lbk = Integer.parseInt(row[45]);
        this.prc = Integer.parseInt(row[46]);
        this.tak = Integer.parseInt(row[47]);
        this.pow = Integer.parseInt(row[48]);
        this.bsh = Integer.parseInt(row[49]);
        this.fmv = Integer.parseInt(row[50]);
        this.pmv = Integer.parseInt(row[51]);
        this.pur = Integer.parseInt(row[52]);
        this.mcv = Integer.parseInt(row[53]);
        this.zcv = Integer.parseInt(row[54]);
        this.prs = Integer.parseInt(row[55]);
        this.ret = Integer.parseInt(row[56]);
        this.kpw = Integer.parseInt(row[57]);
        this.kac = Integer.parseInt(row[58]);
        this.sta = Integer.parseInt(row[59]);
        this.tgh = Integer.parseInt(row[60]);
        this.inj = Integer.parseInt(row[61]);
        this.per = Integer.parseInt(row[62]);
        this.unknown1 = Integer.parseInt(row[63]);
        this.unknown2 = Integer.parseInt(row[64]);
    }

    public int[] getAttributes() {
        int[] ret = {
                this.acc,
                this.agi,
                this.awr,
                this.bcv,
                this.bsh,
                this.bsk,
                this.btk,
                this.car,
                this.cth,
                this.cit,
                this.cod,
                this.fmv,
                this.pow,
                this.ibl,
                this.inj,
                this.jkm,
                this.jmp,
                this.kac,
                this.kpw,
                this.ret,
                this.lbk,
                this.mcv,
                this.pbf,
                this.pbp,
                this.pbk,
                this.per,
                this.pac,
                this.prc,
                this.pmv,
                this.prs,
                this.pur,
                this.rls,
                this.drr,
                this.mrr,
                this.srr,
                this.rbf,
                this.rbp,
                this.rbk,
                this.unknown1,
                this.spc,
                this.spd,
                this.spm,
                this.sta,
                this.sfa,
                this.str,
                this.tak,
                this.dac,
                this.mac,
                this.unknown2,
                this.sac,
                this.run,
                this.thp,
                this.tup,
                this.tgh,
                this.trk,
                this.zcv
        };
        return ret;
    }

}
