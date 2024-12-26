package peice;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Main.GamePanel;
import Main.Type;
import Main.board;

import javax.imageio.ImageIO;

public class Piece {

    public Type type;
    public BufferedImage image;
    public int x,y;
    public int col,row,preCol,preRow;
    public int color;
    public Piece hittingP;
    public boolean moved,twoStepped;

    public Piece (int color, int col, int row){
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }
    public BufferedImage getImage(String imagePath) {
        BufferedImage image = null;
        try {
            if (getClass().getResourceAsStream(imagePath + ".png") == null) {
                throw new IllegalArgumentException("Image not found: " + imagePath + ".png");
            }
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return image;
    }
    public int getX(int col) {
        return col * board.SQUARE_SIZE;
    }
    public int getY(int row) {
        return row * board.SQUARE_SIZE;
    }
    public int getCol(int x) {
        return (x + board.HALF_SQUARE_SIZE)/board.SQUARE_SIZE;
    }
    public int getRow(int y) {
        return (y + board.HALF_SQUARE_SIZE)/board.SQUARE_SIZE;
    }
    public int getIndex(){
        for(int index = 0; index < GamePanel.simpieces.size(); index++){
            if(GamePanel.simpieces.get(index) == this){
                return index;
            }
        }
        return 0;
    }
    public void updatePosition(){

        //to CHECK En Passant
        if(type == Type.PAWN){
            if(Math.abs(row-preRow) == 2){
                twoStepped = true;
            }
        }
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }
    public void resetPosition() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }
    public boolean canMove(int targetCol, int targetRow) {
        return false;
    }
    public boolean isWithinBoard(int targetCol, int targetRow) {
        if(targetCol >=0 && targetCol <= 7 && targetRow >= 0 && targetRow <= 7){
            return true;
        }
        return false;
    }
    public boolean isSameSquare(int targetCol, int targetRow) {
        if(targetCol == preCol && targetRow == preRow){
            return true;
        }
        return false;
    }
    public Piece getHittingP(int targetCol, int targetRow) {
        for(Piece piece : GamePanel.simpieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }
    public boolean isValidSquare(int targetCol, int targetRow) {
        hittingP = getHittingP(targetCol, targetRow);

        if(hittingP == null){  //this square is vacant
            return true;
        }
        else{  // this square if occupied
            if(hittingP.color != this.color){  // if the colour is different it can be captured
                return true;
            }
            else{
                hittingP = null;
            }
        }
        return false;
    }
    public boolean pieceIsOnStraightLine(int targetCol, int targetRow){
        // When this piece is moving to the left
        for(int c = preCol-1; c>targetCol;c--){
            for(Piece piece : GamePanel.simpieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //when this piece is moving to the right
        for(int c = preCol+1; c<targetCol;c++){
            for(Piece piece : GamePanel.simpieces){
                if(piece.col == c && piece.row == targetRow){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //when this piece is moving up
        for(int r = preRow-1; r>targetRow;r--){
            for(Piece piece : GamePanel.simpieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        //When this piece is moving down
        for(int r = preRow+1; r<targetRow;r++){
            for(Piece piece : GamePanel.simpieces){
                if(piece.col == targetCol && piece.row == r){
                    hittingP = piece;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean peiceIsOnDiagonalLine(int targetCol, int targetRow) {
        if (targetRow < preRow) {
            //up left
            for (int c = preCol - 1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
            // Up Right
            for (int c = preCol + 1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == preRow - diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        // Down left
        if (targetRow > preRow) {
            for (int c = preCol - 1; c > targetCol; c--) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
            //Down right
            for (int c = preCol + 1; c < targetCol; c++) {
                int diff = Math.abs(c - preCol);
                for (Piece piece : GamePanel.simpieces) {
                    if (piece.col == c && piece.row == preRow + diff) {
                        hittingP = piece;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, board.SQUARE_SIZE, board.SQUARE_SIZE, null);
    }

}
