import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.*;
import java.util.Vector;
import javax.swing.*;
import java.io.*;

/*
입력, 출력, 검색, 순위, 삭제, 파일저장, 파일 불러오기 기능 구현
 */
class Scorelmple implements Score {
    private ScoreDTO dto; // 한 학생에 대한 정보
    private ScoreForm sf;
    private ArrayList <ScoreDTO> li; // 추가된 데이터
    private ArrayList <ScoreDTO> allList;

    public ScoreImple(ScoreForm sf){
        this.sf = sf;
        li = new ArrayList<ScoreDTO>();
        allList = new ArrayList<ScoreDTO>();
    }

    @Override
    // 입력
    public void insert() {
        dto = new ScoreDTO(sf.getsNumber(), sf.getName(), sf.getKor(), sf.getEng(), sf.getMath());
        li.add(dto);
        allList.add(dto);
    }
    // 출력
    @Override
    public DefaultTableModel outputModel(DefaultTableModel model) {
        if (li.size() != 0) {
            for (ScoreDTO data : li) {
                Vector<Object> v = new Vector<Object>();
                v.add(data.getsNumber());
                v.add(data.getName());
                v.add(data.getKor());
                v.add(data.getEng());
                v.add(data.getMath());
                v.add(data.getTot());
                v.add(data.getAvg());
                model.addRow(v);
            }
        } else {
            while (model.getRowCount() != 0) {
                model.removeRow(0);
            }
            for (ScoreDTO data : allList) {
                Vector<Object> v = new Vector<Object>();
                v.add(data.getsNumber());
                v.add(data.getName());
                v.add(data.getKor());
                v.add(data.getEng());
                v.add(data.getMath());
                v.add(data.getTot());
                v.add(data.getAvg());
                model.addRow(v);
            }
        }
        // 값 초기화
        while (li.size() != 0) {
            li.remove(0);
        }

        return model;
    }
    // 찾기 메서드
    @Override
    public DefaultTableModel search(String sNumber, DefaultTableModel searchModel){

    }
}
