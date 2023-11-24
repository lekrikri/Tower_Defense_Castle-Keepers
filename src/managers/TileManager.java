package managers;

import static helpz.Constants.Tiles.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.LoadSave;
import objects.Tile;

public class TileManager {

	public Tile GRASSFULL,
	ROADTOP1,
	ROADTOP2,
	ROADTOP3,
	ROADFULL,
	ROADBOT1,
	ROADBOT2,
	ROADBOT3,
	ROADTOPLEFT,
	ROADTOPRIGHT,
	ROADRIGHT1,
	ROADRIGHT2,
	ROADRIGHT3,
	ROADLEFT1,
	ROADLEFT2,
	ROADLEFT3,
	ROADBOTLEFT,
	ROADBOTRIGHT,
	CASTLE1,
	CASTLE2,
	CASTLE3,
	CASTLE4,
	CASTLE5,
	CASTLE6,
	CASTLE7,
	CASTLE8,
	CASTLE9,
	GATE1,
	GATE2,
	GATE3,
	GATE4,
	TREE1,
	TREE2,
	TREE3,
	TREE4,
	TRUNK1,
	TRUNK2,
	ROCKA,
	ROCKB,
	GRASS,
	TENT1,
	TENT2,
	WOODROCK,
	DIRT,
	BUSH1,
	BUSH2,
	SIGN;


	private BufferedImage tileset;
	public ArrayList<Tile> tiles = new ArrayList<>();
	public ArrayList<Tile> castles = new ArrayList<>();
	public ArrayList<Tile> roadsS = new ArrayList<>();
	public ArrayList<Tile> roadsC = new ArrayList<>();
	public ArrayList<Tile> corners = new ArrayList<>();
	public ArrayList<Tile> beaches = new ArrayList<>();
	public ArrayList<Tile> islands = new ArrayList<>();

	public TileManager() {

		loadSprites();
		createTiles();

	}

	private void createTiles() {

		int id = 0;

		// 0
        tiles.add(GRASSFULL = new Tile(getSprite(5, 4), id++, GRASS_TILE));
        // 1
        tiles.add(ROADTOP1 = new Tile(getSprite(1, 4), id++, ROAD_TILE));
        // 2
        tiles.add(ROADTOP2 = new Tile(getSprite(2, 4), id++, ROAD_TILE));
        // 3
        tiles.add(ROADTOP3 = new Tile(getSprite(3, 4), id++, ROAD_TILE));
        // 4
        tiles.add(ROADFULL = new Tile(getSprite(2, 1), id++, ROAD_TILE));
        // 5
        tiles.add(ROADBOT1 = new Tile(getSprite(1, 0), id++, ROAD_TILE));
        // 6
        tiles.add(ROADBOT2 = new Tile(getSprite(2, 0), id++, ROAD_TILE));
        // 7
        tiles.add(ROADBOT3 = new Tile(getSprite(3, 0), id++, ROAD_TILE));
        // 8
        tiles.add(ROADTOPLEFT = new Tile(getSprite(1, 1), id++, ROAD_TILE));
        // 9
        tiles.add(ROADTOPRIGHT = new Tile(getSprite(3, 1), id++, ROAD_TILE));
        // 10
        tiles.add(ROADRIGHT1 = new Tile(getSprite(4, 1), id++, ROAD_TILE));
        // 11
        tiles.add(ROADRIGHT2 = new Tile(getSprite(4, 2), id++, ROAD_TILE));
        // 12
        tiles.add(ROADRIGHT3 = new Tile(getSprite(4, 3), id++, ROAD_TILE));
        // 13
        tiles.add(ROADLEFT1 = new Tile(getSprite(0, 1), id++, ROAD_TILE));
        // 14
        tiles.add(ROADLEFT2 = new Tile(getSprite(0, 2), id++, ROAD_TILE));
        // 15
        tiles.add(ROADLEFT3 = new Tile(getSprite(0, 3), id++, ROAD_TILE));
        // 16
        tiles.add(ROADBOTLEFT = new Tile(getSprite(1, 3), id++, ROAD_TILE));
        // 17
        tiles.add(ROADBOTRIGHT = new Tile(getSprite(3, 3), id++, ROAD_TILE));
		 // 18
        tiles.add(CASTLE1 = new Tile(getSprite(7, 2), id++, ROAD_TILE));
		 // 19
        tiles.add(CASTLE2 = new Tile(getSprite(8, 2), id++, ROAD_TILE));
		 // 20
        tiles.add(CASTLE3 = new Tile(getSprite(9, 2), id++, ROAD_TILE));
		 // 21
        tiles.add(CASTLE4 = new Tile(getSprite(7, 1), id++, ROAD_TILE));
		 // 22
        tiles.add(CASTLE5 = new Tile(getSprite(8, 1), id++, ROAD_TILE));
		 // 23
        tiles.add(CASTLE6 = new Tile(getSprite(9, 1), id++, ROAD_TILE));
		 // 24
        tiles.add(CASTLE7 = new Tile(getSprite(7, 0), id++, ROAD_TILE));
		 // 25
        tiles.add(CASTLE8 = new Tile(getSprite(8, 0), id++, ROAD_TILE));
		 // 26
        tiles.add(CASTLE9 = new Tile(getSprite(9, 0), id++, ROAD_TILE));
		 // 27
        tiles.add(GATE1 = new Tile(getSprite(8, 8), id++, ROAD_TILE));
		 // 28
        tiles.add(GATE2 = new Tile(getSprite(9, 8), id++, ROAD_TILE));
		 // 29
        tiles.add(GATE3 = new Tile(getSprite(8, 9), id++, ROAD_TILE));
		 // 30
        tiles.add(GATE3 = new Tile(getSprite(9, 9), id++, ROAD_TILE));
		 // 31
        tiles.add(TREE1 = new Tile(getSprite(0, 10), id++, ROAD_TILE));
		 // 32
        tiles.add(TREE2 = new Tile(getSprite(0, 11), id++, ROAD_TILE));
		 // 33
        tiles.add(TREE3 = new Tile(getSprite(1, 10), id++, ROAD_TILE));
		 // 34
        tiles.add(TREE4 = new Tile(getSprite(1, 11), id++, ROAD_TILE));
		 // 35
        tiles.add(TRUNK1 = new Tile(getSprite(4, 10), id++, ROAD_TILE));
		 // 36
        tiles.add(ROCKA = new Tile(getSprite(3, 10), id++, ROAD_TILE));
		 // 37
        tiles.add(ROCKB = new Tile(getSprite(2, 11), id++, ROAD_TILE));
		 // 38
        tiles.add(GRASS = new Tile(getSprite(6, 10), id++, GRASS_TILE));
		 // 39
        tiles.add(TENT1 = new Tile(getSprite(3, 11), id++, ROAD_TILE));
		 // 40
        tiles.add(TENT2 = new Tile(getSprite(4, 11), id++, ROAD_TILE));
		 // 41
        tiles.add(WOODROCK = new Tile(getSprite(5, 10), id++, ROAD_TILE));
		 // 42
        tiles.add(TRUNK2 = new Tile(getSprite(5, 11), id++, ROAD_TILE));
		 // 43
        tiles.add(DIRT = new Tile(getSprite(6, 11), id++, ROAD_TILE));
		 // 44
        tiles.add(BUSH1 = new Tile(getSprite(7, 11), id++, ROAD_TILE));
		 // 45
        tiles.add(BUSH1 = new Tile(getSprite(7, 10), id++, ROAD_TILE));
		 // 46
        tiles.add(SIGN = new Tile(getSprite(8, 10), id++, ROAD_TILE));

		tiles.addAll(roadsS);
		tiles.addAll(roadsC);
		tiles.addAll(corners);
		tiles.addAll(beaches);
		tiles.addAll(islands);
	}

	private void loadSprites() {
		tileset = LoadSave.getSprites();
	}

	public Tile getTile(int id) {
		return tiles.get(id);
	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getSprite();
	}

	public BufferedImage getAniSprite(int id, int animationIndex) {
		return tiles.get(id).getSprite(animationIndex);
	}

	private BufferedImage[] getAniSprites(int xCord, int yCord) {
		BufferedImage[] arr = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			arr[i] = getSprite(xCord + i, yCord);
		}

		return arr;

	}

	private BufferedImage getSprite(int xCord, int yCord) {
		return tileset.getSubimage(xCord * 32, yCord * 32, 32, 32);
	}

	public boolean isSpriteAnimation(int spriteID) {
		return tiles.get(spriteID).isAnimation();
	}

	public int[][] getTypeArr() {
		int[][] idArr = LoadSave.GetLevelData();
		int[][] typeArr = new int[idArr.length][idArr[0].length];

		for (int j = 0; j < idArr.length; j++) {
			for (int i = 0; i < idArr[j].length; i++) {
				int id = idArr[j][i];
				typeArr[j][i] = tiles.get(id).getTileType();
			}
		}

		return typeArr;

	}

	public ArrayList<Tile> getRoadsS() {
		return roadsS;
	}

	public ArrayList<Tile> getRoadsC() {
		return roadsC;
	}

	public ArrayList<Tile> getCorners() {
		return corners;
	}

	public ArrayList<Tile> getBeaches() {
		return beaches;
	}

	public ArrayList<Tile> getIslands() {
		return islands;
	}

}
