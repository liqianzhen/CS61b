public class NBody {
	public static double readRadius(String name) {
		In file = new In(name);
		int planetNumber=file.readInt();
		double radius=file.readDouble();
		return radius;
	}

	public static Planet [] readPlanets(String name){
		In file = new In(name);
		int planetNumber=file.readInt();
		Planet [] allplanet=new Planet[planetNumber];
		double radius=file.readDouble();
		for (int i=0;i<planetNumber;i++) {
			double iniXP=file.readDouble();
			double iniYP=file.readDouble();
			double iniXV=file.readDouble();
			double iniYV=file.readDouble();
			double mass=file.readDouble();
			String pic=file.readString();
		allplanet[i]=new Planet(iniXP,iniYP,iniXV,iniYV,mass,pic); 	
		}
		return allplanet;
	}
	public static void main(String[] args) {
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double radiusUniverse=NBody.readRadius(filename);
		Planet [] allplanets=NBody.readPlanets(filename);
		StdDraw.setXscale(-radiusUniverse,radiusUniverse);
		StdDraw.setYscale(-radiusUniverse,radiusUniverse);
		StdDraw.picture(0,0,"./images/starfield.jpg");
		StdAudio.loop("./audio/2001.mid");

		for (int index=0; index<allplanets.length; index++) {
			allplanets[index].draw();
		}

		for (double i=0; i<T; i=i+dt) {
			double [] xForces=new double [allplanets.length];
			double [] yForces=new double [allplanets.length];
			
		for (int n=0; n<allplanets.length; n++) {
			xForces[n]=allplanets[n].calcNetForceExertedByX(allplanets);
			yForces[n]=allplanets[n].calcNetForceExertedByY(allplanets);
		    }

		for (int m=0; m<allplanets.length; m++) {
			allplanets[m].update(dt,xForces[m],yForces[m]);
		}
			StdDraw.clear();
			StdDraw.picture(0,0,"./images/starfield.jpg");
		
		for (int g=0; g<allplanets.length; g++) {
			allplanets[g].draw();
		}
		    StdDraw.show(10);
		}
		StdOut.printf("%d\n", allplanets.length);
		StdOut.printf("%.2e\n", radiusUniverse);
		for (int i = 0; i < allplanets.length; i++) {
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		allplanets[i].xxPos, allplanets[i].yyPos, allplanets[i].xxVel, allplanets[i].yyVel, allplanets[i].mass, allplanets[i].imgFileName);	
		}		
	}
}