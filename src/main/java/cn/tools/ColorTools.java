package cn.tools;

import java.awt.Color;
import java.util.Random;

public class ColorTools {
	public static Color getRandColor(Random random,int fc,int bc)
	{
		if(fc>255) fc=255;
		if(bc>255) bc=255;
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
}
