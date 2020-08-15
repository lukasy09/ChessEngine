package com.agh.technology.chess.engine.model.state;

public class BoardState {

    private BlackState blackState;
    private WhiteState whiteState;

    public BlackState getBlackState() {
        return blackState;
    }

    public void setBlackState(BlackState blackState) {
        this.blackState = blackState;
    }

    public WhiteState getWhiteState() {
        return whiteState;
    }

    public void setWhiteState(WhiteState whiteState) {
        this.whiteState = whiteState;
    }
}
