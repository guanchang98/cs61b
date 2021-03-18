import com.sun.source.tree.NewArrayTree;

public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel ;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        b.xxPos = xxPos;
        b.yyPos = yyPos;
        b.xxVel = xxVel;
        b.yyVel = yyVel;
        b.mass = mass;
        b.imgFileName = imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = Math.abs(this.xxPos - b.xxPos);
        double dy = Math.abs(this.yyPos - b.yyPos);
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Body b) {
        double f = G * this.mass * b.mass / (calcDistance(b) * calcDistance(b));
        return f;
    }

    public double calcForceExertedByX (Body b) {
        double fx = calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
        return fx;
    }

    public double calcForceExertedByY (Body b) {
        double fy = calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
        return fy;
    }

    public double calcNetForceExertedByX (Body[] allBodies) {
        double fXSum = 0;
        for (int i = 0; i < allBodies.length; i++) {
            if (!allBodies[i].equals(this)) {
                fXSum += this.calcForceExertedByX(allBodies[i]);
            }
        }
        return fXSum;
    }

    public double calcNetForceExertedByY (Body[] allBodies) {
        double fYSum = 0;
        for (int i = 0; i < allBodies.length; i++) {
            if (!allBodies[i].equals(this)) {
                fYSum += this.calcForceExertedByY(allBodies[i]);
            }
        }
        return fYSum;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel = this.xxVel + fX * dt / this.mass;
        this.yyVel = this.yyVel + fY * dt / this.mass;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}