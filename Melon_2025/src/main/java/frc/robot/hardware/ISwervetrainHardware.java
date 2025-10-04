package frc.robot.hardware;

public interface ISwervetrainHardware {

    public void move(double forward, double turn, double w);

    public double getEncoderPos();

    public void resetEncoders();

    public double getGyroAngle();

    public void resetGyro();

}
