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

    public Scorelmple(ScoreForm sf){
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

    // search 찾기 메서드
    @Override
    public DefaultTableModel search(String sNumber, DefaultTableModel searchModel){
        for (int i = 0 ; i < searchModel.getRowCount() ; i++){
            if(!sNumber.equals(searchModel.getValueAt(i,0))){
                searchModel.removeRow(i);
                i =- 1;
            }
        }
        return searchModel;
    }

    // 정렬(순위) sort
    @Override
    public DefaultTableModel to_desc(DefaultTableModel model) {
        Collections.sort(allList);
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
            System.out.println(model.getRowCount());
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

    // 삭제
    @Override
    public DefaultTableModel delete(String sNum, DefaultTableModel model){
        for (int i = 0 ; i < allList.size() ; i++){
            if(sNum.equals(allList.get(i).getsNumber())){
                allList.remove(i);
                break;
            }
        }
        return outputModel(model);
    }

    // 저장
    @Override
    public void save() {
        Object o = allList;
        File file = null;
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(sf);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }
        if (file != null) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(o);
                oos.close();
                oos.close();
            } catch (IOException e) {
            }
        }
    }

    // 파일 열기
    @Override
    public DefaultTableModel load(DefaultTableModel model){
        try{
            File file = null;
            JFileChooser c = new JFileChooser();
            int result = c.showOpenDialog(sf);
            if(result == JFileChooser.APPROVE_OPTION) {
                file = c.getSelectedFile();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object o = ois.readObject();
                allList = (ArrayList<ScoreDTO>) o;
                fis.close();
                ois.close();
            }
        } catch(IOException | ClassNotFoundException e){ e.printStackTrace(); }
        return outputModel(model);
    }
}
