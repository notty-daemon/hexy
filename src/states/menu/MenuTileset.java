package states.menu;

import java.awt.*;

import core.Tile;
import core.Tileset;

public class MenuTileset extends Tileset {

    private int screenHeight;
    private int[][] zPositions;

    public MenuTileset(int screenHeight) {
        super(11, 11);

        offsetX = 25;
        offsetY = 80;

        this.screenHeight = screenHeight;

        generateTiles();
    }

    @Override
    public void generateTiles() {
        for (int iX = 0; iX < horizontalSize; iX++) {
            for (int iY = 0; iY < verticalSize; iY++) {
                tileset[iX][iY] = new MenuTile("hexyAssets/tiles/1.png", iX, iY, g);
                tileset[iX][iY].offsetZ = 200 * (iX + iY) + 100 * (10 - iX) + 100;
                tileset[iX][iY].hex = getHex(tileset[iX][iY].centerX, tileset[iX][iY].centerY, tileset[iX][iY].tileWidth);
            }
        }
    }

    @Override
    protected void renderTileset(Graphics2D g) {
        renderTiles(g);
    }


    @Override
    protected Tile renderTile(Tile tile, Graphics2D g) {
        MenuTile menuTile = (MenuTile) tile;
//        if (menuTile.positionZ > 0)
//            menuTile.positionZ -= 50;

        g.drawImage(tile.image, tile.positionX + offsetX, tile.positionY + offsetY + menuTile.offsetZ, null);

        for (MenuTile.Flower flower : menuTile.flowers) {
            g.drawImage(flower.image, tile.positionX + flower.positionX + offsetX, tile.positionY + flower.positionY + offsetY + menuTile.offsetZ, null);
        }

        if (menuTile.tree != null) {
            MenuTile.Tree tree = menuTile.tree;
            g.drawImage(tree.image, tile.positionX + tree.positionX + offsetX, tile.positionY + tree.positionY + offsetY + menuTile.offsetZ, null);
        }
        return menuTile;
    }
}
