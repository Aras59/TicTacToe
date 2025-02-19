package org;

import javax.swing.*;

public class Cell extends JButton {

    private String sign;
    private boolean isClicked;

    public Cell() {

    }

    public Cell(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
