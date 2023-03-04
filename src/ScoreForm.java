
/*
외관 구현
자바 gui, "swing" 활용
윈도우 창에 화면 띄우기
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JTable;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

class ScoreForm extends JFrame implements ActionListener {
    private JLabel sNumberL, nameL, korL, engL, mathL;
    private JLabel[] allLabel;
    private JButton bt1, bt2, bt3, bt4, bt5, bt6, bt7;
    private JButton[] allBtn;
    private JTextField sNumberT, nameT, korT, engT, mathT;
    private JTextField[] allText;
    private DefaultTableModel model;
    private Vector<String> head;
    private JTable table;
    private Scorelmple si;

    public ScoreForm() {
        si = new Scorelmple(this);
        // 하단 버튼 생성
        JPanel bottom = new JPanel(new GridLayout(1, 5, 0, 0));
        bt1 = new JButton("입력");
        bt2 = new JButton("출력");
        bt3 = new JButton("학번검색");
        bt4 = new JButton("정렬");
        bt5 = new JButton("삭제");
        bt6 = new JButton("파일저장");
        bt7 = new JButton("파일열기");
        allBtn = new JButton[]{bt1, bt2, bt3, bt4, bt5, bt6, bt7};
        // 버튼 패널에 붙이기
        for (int i = 0; i < allBtn.length; i++) {
            bottom.add(allBtn[i]);
        }
        add("South", bottom); // 붙이기

        // 테이블 생성
        head = new Vector<String>();
        head.add("학번");
        head.add("이름");
        head.add("국어");
        head.add("영어");
        head.add("수학");
        head.add("총점");
        head.add("평균");
        model = new DefaultTableModel(head, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // 중앙 레이아웃
        JPanel center = new JPanel(new GridLayout(1, 2, 0, 0));
        JPanel center1 = new JPanel(new GridLayout(5, 1, 0, 0)); // 라벨과 텍스트필드 담김
        JPanel[] leftP = new JPanel[5];
        for (int i = 0; i < leftP.length; i++) {
            leftP[i] = new JPanel();
        }

        // 라벨 설정
        sNumberL = new JLabel("학번");
        nameL = new JLabel("이름");
        korL = new JLabel("국어");
        engL = new JLabel("영어");
        mathL = new JLabel("수학");
        allLabel = new JLabel[]{sNumberL, nameL, korL, engL, mathL};
        // 텍스트 필드 설정
        sNumberT = new JTextField("", 20);
        nameT = new JTextField("", 20);
        korT = new JTextField("", 20);
        engT = new JTextField("", 20);
        mathT = new JTextField("", 20);
        allText = new JTextField[]{sNumberT, nameT, korT, engT, mathT};
        // 패널에 라벨과 텍스트 필드 올리기
        for (int i = 0; i < leftP.length; i++) {
            leftP[i].add("West", allLabel[i]);
            leftP[i].add("Center", allText[i]);
        }
        // 프레임 위에 올리기
        for (int i = 0; i < 5; i++) {
            center1.add(leftP[i]);
        }
        center.add(center1);
        center.add(scroll);
        add(center);


        // 윈도우 창 설정
        setBounds(300, 500, 700, 200);
        setVisible(true);
        // 버튼에 이벤트 설정
        for (int i = 0; i < allBtn.length; i++) {
            allBtn[i].addActionListener(this);
        }

        //이벤트
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) { // 입력
            JOptionPane.showConfirmDialog(ScoreForm.this, "정보를 입력합니다", "정보", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
            si.insert();
            for (int i = 0; i < allText.length; i++) {
                allText[i].setText("");
            }
        } else if (e.getSource() == bt2) { // 출력
            model = si.outputModel(model);
        } else if (e.getSource() == bt3) { // 학번 검색
            String answer = JOptionPane.showInputDialog(this, "학번을 입력하세요");
            if (answer != null) model = si.search(answer, model);
        } else if (e.getSource() == bt4) { // 정렬
            model = si.to_desc(model);
        } else if (e.getSource() == bt5) { // 삭제
            String answer = JOptionPane.showInputDialog(this, "삭제할 학번을 입력하세요");
            if (answer != null) model = si.delete(answer, model);
        } else if (e.getSource() == bt6) { // 파일 저장
            si.save();
        } else if (e.getSource() == bt7) { // 파일 열기
            model = si.load(model);
        }
    }

    public String getsNumber(){ return sNumberT.getText(); }
    public String getName(){ return nameT.getText(); }
    public int getKor(){ return Integer.parseInt(korT.getText()); }
    public int getEng(){ return Integer.parseInt(engT.getText()); }
    public int getMath(){ return Integer.parseInt(mathT.getText()); }
}