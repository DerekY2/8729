// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.interfaces.Gyro;


public class DriveSubsystem extends SubsystemBase {

  int state=0;
  int debounceCount=0;
  double onChargeStationDegree = 13.0;
  double debounceTime = 0.2;
  double robotSpeedSlow=0.325;
  double robotSpeedFast=0.65;
  double levelDegree=6.0;
  boolean toggle = false;
  boolean up = false;
  boolean down = false;
  int autoCounter=0;
  boolean mid = false;
  double slowDrive = 1.0;
  double slowTurn = 1.0;
  double high = -219;
  double middle = -116;
  int angle = 5;
  boolean turn = false;
  boolean align = false;
  double turnsetpoint = 0.0;
  boolean elevatorSlowDrive = false;
  boolean toggleSlowDrive = false;

    // Initialize driver & operator controllers
    final Joystick JOYSTICK = new Joystick(0);
    final Joystick JOYSTICK2 = new Joystick(1);
  
    // Set motor controller connections: Drivetrain motor > SPX > RoboRIO
    final WPI_VictorSPX FRONT_LEFT = new WPI_VictorSPX(4);
    final WPI_VictorSPX FRONT_RIGHT = new WPI_VictorSPX(6);
    final WPI_VictorSPX BACK_LEFT = new WPI_VictorSPX(12);
    final WPI_VictorSPX BACK_RIGHT = new WPI_VictorSPX(5);
  
    // Tank
    final MotorControllerGroup LEFT_DRIVE = new MotorControllerGroup(FRONT_LEFT, BACK_LEFT);
    final MotorControllerGroup RIGHT_DRIVE = new MotorControllerGroup(FRONT_RIGHT, BACK_RIGHT);
  
    // Initialize encoder
    final Encoder encoderA = new Encoder(1, 2);
    // final Encoder encoderB = new Encoder(2, 3);
  
    // Setup Differential Drive
    final DifferentialDrive ROBOT_DRIVE = new DifferentialDrive(LEFT_DRIVE, RIGHT_DRIVE);
  
    // initialize gyro
    final AHRS gyro = new AHRS(SPI.Port.kMXP);

    
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

      //invert corresponding motors
      RIGHT_DRIVE.setInverted(true);
      encoderA.reset();

  }

  /*
  * @param xSpeed        Speed of the robot in the x direction (forward).
  * @param rot           Angular rate of the robot.
  */

  public void toggleSlowDrive(double drive, double turn){
    slowDrive = drive;
    slowTurn = turn;
  }

  public void setNeutralModes(){
      FRONT_LEFT.setNeutralMode(NeutralMode.Brake);
      FRONT_RIGHT.setNeutralMode(NeutralMode.Brake);
      BACK_LEFT.setNeutralMode(NeutralMode.Brake);
      BACK_RIGHT.setNeutralMode(NeutralMode.Brake);
  }

  public void drive(double xSpeed, double rot){

      ROBOT_DRIVE.arcadeDrive(xSpeed*slowDrive, rot*slowTurn);
  }

  public double getRoll(){
    return gyro.getRoll();
  }

  public double getPitch(){
    return gyro.getPitch();
  }

  public double getYaw(){
    return gyro.getYaw();
  }

  public double getEncoderDistance(){
    return encoderA.getDistance();
  }

  public void resetEncoder(){
    encoderA.reset();
  }

  public void sendToDashboard(String key, double value){
    SmartDashboard.putNumber(key, value);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    

    //SmartDashboard.putNumber("Elevator Encoder", m_elevatorEncoder.getPosition());
    //SmartDashboard.putBoolean("Limit Switch", toplimitswitch.get());
    SmartDashboard.putNumber("EncoderA", encoderA.getDistance());
    // SmartDashboard.putNumber("EncoderB", encoderB.getDistance());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }
}
