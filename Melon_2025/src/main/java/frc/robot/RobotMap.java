package frc.robot;

public final class RobotMap {
  
  // Controller Ports
  public static final int DRIVECON = 0;
  public static final int OPCON = 1;

  // Swervetrain CAN IDs
  public static final int DRIVE_L1_FORWARD_CANID = 1;
  public static final int DRIVE_L1_TURNING_CANID = 2;
  public static final int DRIVE_L2_FORWARD_CANID = 3;
  public static final int DRIVE_L2_TURNING_CANID = 4;
  public static final int DRIVE_R1_FORWARD_CANID = 5;
  public static final int DRIVE_R1_TURNING_CANID = 6;
  public static final int DRIVE_R2_FORWARD_CANID = 7;
  public static final int DRIVE_R2_TURNING_CANID = 8;

  // Encoder DIO Ports
  public static final int DIO_PORT_A_LEFT_ENCODER = 1;
  public static final int DIO_PORT_B_LEFT_ENCODER = 2;

  public static final int DIO_PORT_A_RIGHT_ENCODER = 3;
  public static final int DIO_PORT_B_RIGHT_ENCODER = 4;


  // Drivetrain CAN IDs
  public static final int DRIVE_L1_CANID = 9;
  public static final int DRIVE_L2_CANID = 10;
  public static final int DRIVE_R1_CANID = 11;
  public static final int DRIVE_R2_CANID = 12;

  // Robot Size, in meters
  public static final double ROBOT_LENGTH = 1.75;
  public static final double ROBOT_WIDTH = 1.75;
}
