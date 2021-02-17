import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GUI extends JFrame {
    private JPanel MainPanel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a5Button;
    private JButton a4Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton buttonEquals;
    private JButton buttonAdd;
    private JButton buttonSubtract;
    private JButton buttonMultiply;
    private JButton buttonDivide;
    private JButton apButton;
    private JTextField InputField;
    private JButton buttonClear;

    public BigDecimal firstNum = null;
    public BigDecimal secondNum = null;
    public Double result = null;
    public String operation = "";
    public Boolean operationActive = false;

    public GUI() {
        add(MainPanel);
        setTitle("Calculator with no bug whatsoever");
        setSize(300, 400);
        setResizable(false);
        setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
        a1Button.addActionListener(e -> numPress("1"));
        a2Button.addActionListener(e -> numPress("2"));
        a3Button.addActionListener(e -> numPress("3"));
        a4Button.addActionListener(e -> numPress("4"));
        a5Button.addActionListener(e -> numPress("5"));
        a6Button.addActionListener(e -> numPress("6"));
        a7Button.addActionListener(e -> numPress("7"));
        a8Button.addActionListener(e -> numPress("8"));
        a9Button.addActionListener(e -> numPress("9"));
        a0Button.addActionListener(e -> numPress("0"));
        apButton.addActionListener(e -> numPress("."));

        buttonEquals.addActionListener(e -> {
            if(!InputField.getText().isEmpty() && !operationActive){
                if(secondNum==null && firstNum != null)
                    secondNum = new BigDecimal(InputField.getText());
                operationActivate(operation);
            }
        });
        buttonAdd.addActionListener(e -> operationPress("Add"));
        buttonSubtract.addActionListener(e -> operationPress("Subtract"));
        buttonMultiply.addActionListener(e -> operationPress("Multiply"));
        buttonDivide.addActionListener(e -> operationPress("Divide"));
        buttonClear.addActionListener(e -> {
            firstNum=null;
            secondNum=null;
            operation = "";
            operationActive = false;
            InputField.setText("");
        });
    }
    public void numPress(String num){
        if(operationActive){
            InputField.setText(null);
            operationActive=false;
        }
        InputField.setText(InputField.getText() + num);
    }
    public void operationPress(String operationType){
        if(!InputField.getText().isEmpty() && !operationActive){
            if(secondNum == null && firstNum != null){
                secondNum = new BigDecimal(InputField.getText());
                operationActivate(operationType);
            }
            operationActive = true;
            firstNum = new BigDecimal(InputField.getText());
            secondNum = null;
            operation = operationType;
        }
    }
    public boolean isInt(BigDecimal a){
        return a.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }
    public void operationActivate(String ot) {
        switch (ot) {
            case "Add" -> {
                firstNum = firstNum.add(secondNum);
                AutoIntDouble();
            }
            case "Subtract" -> {
                firstNum = firstNum.subtract(secondNum);
                AutoIntDouble();
            }
            case "Multiply" -> {
                firstNum = firstNum.multiply(secondNum);
                AutoIntDouble();
            }
            case "Divide" -> {
                if(secondNum.intValue() == 0){
                    System.out.println("No divide by 0 please.");
                }
                else {
                    firstNum = firstNum.divide(secondNum, 20, RoundingMode.HALF_UP);
                    AutoIntDouble();
                }
            }
            default -> {
                firstNum = new BigDecimal(InputField.getText());
                AutoIntDouble();
            }
        }
    }
    public void AutoIntDouble(){
        if (isInt(firstNum))
            InputField.setText(String.valueOf(firstNum.intValue()));
        else
            InputField.setText(String.valueOf(firstNum));
    }
}
