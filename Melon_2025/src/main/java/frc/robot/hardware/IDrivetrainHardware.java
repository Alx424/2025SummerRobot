package frc.robot.hardware;

public interface IDrivetrainHardware {
    public void setLeftSide(double val);

    public void setRightSide(double val);

    public double getEncoderPos();

    public void resetEncoders();

    public double getGyroAngle();

    public void resetGyro();

    public double capInput(double val, double min, double max);
}
