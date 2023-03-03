/* 인터페이스이자 추상클래스
기능적인 요소를 담당하는 추상메소드 선언
 */

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
interface Score {
    void insert(); // 입력 메소드
    DefaultTableModel search(String sNumber, DefaultTableModel searchModel); //검색 메소드
    DefaultTableModel to_desc(DefaultTableModel model); // 순위메서드(총점 내림차순)
    DefaultTableModel delete(String sNumber, DefaultTableModel model); // 삭제 메서드
    void save(); // 저장 메서드
    DefaultTableModel load(DefaultTableModel model); // 출력 메서드
}
