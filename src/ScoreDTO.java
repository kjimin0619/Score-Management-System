import java.util.*;
import java.io.*;
/*
* 한 사람분의 성적 정보 저장.
* 이름, 학번, 국어, 영어, 수학, 총점, 평균 정보
*
*
* */

public class ScoreDTO implements Serializable, Comparable <ScoreDTO> {
    // 객체 직렬화를 위한 Serializable 인터페이스 implements, 정렬기준을 세우기 위한 compare 메소드
    private String sNumber;
    private String name;
    private int kor;
    private int eng;
    private int math;
    private int tot = kor + eng + math;
    private double avg = tot/3;

    // 생성자
    public ScoreDTO(String sNumber, String name, int kor, int eng, int math) {
        this.name = name;
        this.sNumber = sNumber;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.tot = this.kor + this.eng + this.math;
        this.avg = tot/3;
    }

    // getter
    public String getName() {return this.name;}
    public String getsNumber() {return this.sNumber;}
    public int getKor() {return this.kor;}
    public int getEng() {return this.eng;}
    public int getMath() {return this.math;}
    public int getTot() {return this.tot;}
    public double getAvg() {return this.avg;}

    @Override
    public int compareTo(ScoreDTO O){
        if (getTot() < o.getTot()) return -1;

    }
    @Override
    public String toString() {
        return hak + "," + name + "," + kor + "," + eng + "," + math + "," + tot + "," + avg;
    }
    private ArrayList<ScoreDTO> allList; // 모든 학생들의 ScoreDTO를 담는 리스트

    public static void save(ArrayList<ScoreDTO> studentList) throws IOException {
        FileOutputStream fos = new FileOutputStream("저장할 파일명");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(allList);


    }

    // 파일 로딩
    public static ArrayList<ScoreDTO> loading(ArrayList<ScoreDTO rallList> throws FileNotFoundException, IOException){
        FileInputStream fis = new FileInputStream("파일명");
        ObjectInputStream ois = new ObjectInputStream(fis);

        allList = (ArrayList<ScoreDTO>)ois.readObject();

        fis.close();
        ois.close();



    }
    
}
