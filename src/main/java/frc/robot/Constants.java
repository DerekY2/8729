// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  /***********************Elevator constants***********************/
  public static final class ElevatorConstants{

    public static final double kSpeedDown = 0.6/2; // elevator descending speed
    public static final double kSpeedUp = -1.2/2; // elevator ascending speed
    public static final double kCorrectionSpeed = 0.2/2; // descending speed when elevator hits limit switch
    public static final double kSpeedMid = 0.3; // actual speed is: kSpeedMid * timer.get()

    public static final double defaultSpeed = 0.0; // when no cmd

  }

  /*********************Intake joint constants*********************/
  public static final class IntakePart1Constants{


    public static final double kRotationSpeedUp = 0.95; // Intake upwards rotation speed
    public static final double kRotationSpeedDown = -0.55; // Intake downwards rotation speed

    public static final double idol = 0.15; // idle speed

    public static final double rotationLock = 0.0; // stop motors
  }

  /*********************Intake roller constants*********************/
  public static final class IntakePart2Constants{

    public static final double kCubeIn = 0.4; // Cube intake speed (cone out)
    public static final double idolCubeIn = 0.2; // cube intake idle speed (cone out)

    public static final double kCubeOut = -0.5; // cube outtake speed (cone in)
    public static final double idolCubeOut = -0.2; // idle cube outtake idle speed (cone in)

    public static final double defaultSpeed = 0.0; // when no cmd
    public static final double defaultIdolSpeed = 0.0; // default idle speed

  }

  /***********************Drive constants************************/
  public static final class DriveConstants{

    // regular drive speeds (x1.0 multiplier)
    public static final double kDriveSpeed = 1.0;
    public static final double kTurnSpeed = 1.0;

    // slow drive speeds (x0.5, x0.8 multiplier)
    public static final double slowDriveSpeed = 0.50;
    public static final double slowTurnSpeed = 0.80;

    // default angular setpoint
    public static final double kSetpoint = 90;
    public static final double noSetpoint = 0; // setpoint to 0, should not change at all


  }

  /*********************Limelight constants*********************/
  public static final class LimelightConstants{

    public static final double kAlignmentSpeed = 0.45;

    // default value to record if none is received from limelight
    public static final double defaultValue = 4;

    // end conditions kErrorSmallest < error < kErrorLargest
    public static final double kErrorSmallest = -4;
    public static final double kErrorLargest = 0;

  }

  /*********************Auto constants*********************/
  public static final class AutoConstants{
    public static final double kInterrupted = 0.0; // this should not change
    
  }

  /*********************Unused constants*********************/
  /*
   * Just for safekeeping; imported from the original timed code, however these are no longer used.
   * 
   */
  public static final class ArchivedConstants{

    public final static int state=0;

    public final static int debounceCount=0;
    public final static double debounceTime = 0.2;

    public final static double onChargeStationDegree = 13.0;
    public final static double levelDegree=6.0;

    public final static double robotSpeedSlow=0.325;
    public final static double robotSpeedFast=0.65;

    public final static boolean maxElevatorHeight = false;

    public final static int autoCounter=0;

    public final static double kp=0.01;
    public final static double ki=0.00;
    public final static double iLimit = 2;
    public final static  double kd=0.00;
    public final static double dt = 0.0;

    public final static double sensorposition = 0.0;

    public final static double errorSum = 0.0;
    public final static double errorRate = 0.0;

    public final static double lasterror = 0.0;
    public final static double lastTimestamp = 0.0;
    public final static double lastpitch = 0.0;

    public final static boolean mid = false;
    public final static double high = -219;
    public final static double middle = -116;

    public final static int angle = 5;
  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
