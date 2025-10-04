package frc.robot.hardware;

import frc.robot.RobotMap;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.autonomous.PIDController;
import edu.wpi.first.wpilibj.Encoder;

public class SwerveModule {
    int drivingCAN;
    int turningCAN;
    double robotLength;
    double robotWidth;

    SparkLowLevel forwardMotor;
    SparkLowLevel turningMotor;

    Encoder turningEncoder;
    Encoder forwardEncoder;

    PIDController turnPID;

    public SwerveModule(int drivingCAN, int turningCAN, int xRotSign, int yRotSign) {
        // robotLength and robotWidth are used to find out how far the wheels are from the center of the robot, used for culculating for for spinning
        
        /*
        xRotSign and yRotSign are used to calculate what direction the wheels should turn to make the robot spin 
        ex// for L1, `outputx = translationX + w*halfWidth;` and `outputy = translationY + w*halfLength;`
        while for L2, `outputx = translationX - w*haldWidth;` and `outputy = translationY + w*halfLength;`
        */
        
        this.drivingCAN = drivingCAN;
        this.turningCAN = turningCAN;
        this.robotLength = RobotMap.ROBOT_LENGTH; // in meters
        this.robotWidth = RobotMap.ROBOT_WIDTH; // in meters
        forwardMotor = new SparkMax(drivingCAN, MotorType.kBrushless);
        turningMotor = new SparkMax(turningCAN, MotorType.kBrushless);
    }

    public void setMotor(double[] output) {
        forwardMotor.set(capPower(output[0]));
        turningMotor.set(turnPID.getOutput()); // uses current PID controller to set wheel turning speed
    }

    public double[] getOutputVal(double forward, double turn, double w) { // w is rotational velocity in rad/s clockwise
        double out, out_ang = 0; // output in magnitude and angle

        // components of translation:
        double Xtranslation = forward*Math.cos(turn);
        double Ytranslation = forward*Math.sin(turn);

        // find and combine components of output vector:
        double x = Xtranslation + w*robotLength/2;
        double y = Ytranslation + w*robotWidth/2;
        out = magnitude(x, y); // calculates magnitude of output vector
        out_ang = radToDeg(Math.atan2(y, x)); // calculates angle of output vector

        if (out_ang % 180 > 90) { // confines to shortest angle between -90 and 90, flips direction if needed
            out_ang -= 180;
            out = -out;
        } else if (out_ang % 180 < -90) {
            out_ang += 180;
            out = -out;
        }

        turnPID = new PIDController(getWheelAngle(), out_ang, -1, 1, 0.0035, 0, 0.01); // pid values to be found manually
        double[] ans = {out, out_ang}; // returns output speed and wheel angle
        return ans;
    }

    public void updateTurningError() {
        turnPID.updateError(getWheelAngle());
        return;
    }

    public double getWheelAngle() {
        double angleDeg = radToDeg(turningEncoder.getDistance())%360; // assuming the encoder is in radians, converts to deg
        return angleDeg;
    }

    public void resetEncoders() {
        forwardEncoder.reset();
        turningEncoder.reset();
        return;
    }

    private double magnitude(double a, double b) {
        return Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2));
    }

    private double radToDeg(double in) {
        return in*180/Math.PI;
    }

    private double capPower(double in) {
        if (in < -1) return -1;
        if (in > 1) return 1;
        return in;
    }
}
