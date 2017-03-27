public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Planet(Planet p) {
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	public double calcDistance (Planet s) {
		double distance=(s.xxPos-xxPos)*(s.xxPos-xxPos)+(s.yyPos-yyPos)*(s.yyPos-yyPos);
		distance=Math.sqrt(distance);
		return distance;
	}

	public double calcForceExertedBy (Planet q) {
		double force= 6.67E-11*mass*q.mass/calcDistance(q)/calcDistance(q);
		return force;
	}

	public double calcForceExertedByX (Planet x) {
		double forceX=(x.xxPos-xxPos)/calcDistance(x)*calcForceExertedBy(x);
		return forceX;
	}

	public double calcForceExertedByY (Planet m) {
		double forceY=(m.yyPos-yyPos)/calcDistance(m)*calcForceExertedBy(m);
		return forceY;
	}

	public double calcNetForceExertedByX (Planet[] planetGroup){
		int index=0;
		double netforceX=0;
		while (index<planetGroup.length) {
			if (equals(planetGroup[index])){
			} else {
				netforceX=netforceX+calcForceExertedByX(planetGroup[index]);
			}
			index=index+1;

		}
		return netforceX;

	}

	public double calcNetForceExertedByY (Planet[] planetGroup){
		int index=0;
		double netforceY=0;
		while (index<planetGroup.length) {
			if (equals(planetGroup[index])){
			} else {
				netforceY=netforceY+calcForceExertedByY(planetGroup[index]);
			}
			index=index+1;
		}
		return netforceY;
	}

	public void update (double dt,double fX,double fY) {
		double aceleX=fX/mass;
		double aceleY=fY/mass;
		xxVel=xxVel+dt*aceleX;
		yyVel=yyVel+dt*aceleY;
		xxPos=xxPos+dt*xxVel;
		yyPos=yyPos+dt*yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
	}
}