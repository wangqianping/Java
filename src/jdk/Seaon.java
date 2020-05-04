package jdk;

public enum Seaon {

    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINNTER("冬天");


    private String desc;

    Seaon(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return desc;
    }

    public String getCode() {
        return this.toString();
    }
}

class Test {

    public static void main(String[] args) {
        Seaon seaon = Seaon.SPRING;
        String code = seaon.getCode();
        String name = seaon.getName();
        System.out.println(code);
        System.out.println(name);

        System.out.println("=============");

        Seaon[] values = Seaon.values();
        for(Seaon seaon1:values){
            System.out.println(seaon1.toString());
        }

        System.out.println("=============");

        Seaon seaon1 = Seaon.valueOf("SPRING");
        System.out.println(seaon1.getCode());
    }
}