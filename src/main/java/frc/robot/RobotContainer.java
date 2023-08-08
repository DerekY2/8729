// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.IntakePart1Constants;
import frc.robot.Constants.IntakePart2Constants;
import frc.robot.Constants.LimelightConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.AlignmentCmd;
import frc.robot.commands.ArcadeDriveCmd;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.SlowDriveCmd;
import frc.robot.commands.TurnToSetpointCmd;
import frc.robot.commands.ElevatorCmd;
import frc.robot.commands.IntakeRotationCmd;
import frc.robot.commands.AutoCommands.commandGroups.AutoCubeEngage;
import frc.robot.commands.AutoCommands.commandGroups.AutoCubeMobility;
import frc.robot.commands.AutoCommands.commandGroups.AutoConeMobility;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.subsystems.IntakeChanPart2;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ElevatorSubsystem m_elevatorSubsystem = new ElevatorSubsystem();
  private final LimelightSubsystem m_limelightSubsystem = new LimelightSubsystem();
  private final IntakeChanPart1 m_intakeChanPart1 = new IntakeChanPart1();
  private final IntakeChanPart2 m_intakeChanPart2 = new IntakeChanPart2();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
  private final Joystick JOYSTICK = new Joystick(0);
  private final Joystick JOYSTICK2 = new Joystick(1);
  //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  // Choose auto sequence
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  // cube + engage
  private final Command m_cubeEngage = new AutoCubeEngage(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);
  // cube + mobility
  private final Command m_cubeMobility = new AutoCubeMobility(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);
  // cone + mobility
  private final Command m_coneMobility = new AutoConeMobility(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_driveSubsystem.setDefaultCommand(new ArcadeDriveCmd(m_driveSubsystem, () -> JOYSTICK.getRawAxis(1), () -> -JOYSTICK.getRawAxis(4)));
    m_elevatorSubsystem.setDefaultCommand(new ElevatorCmd(m_elevatorSubsystem, ElevatorConstants.kElevatorInterrupted));
    m_intakeChanPart1.setDefaultCommand(new IntakeRotationCmd(m_intakeChanPart1, IntakePart1Constants.kRotationOff, IntakePart1Constants.kStallOff));
    m_intakeChanPart2.setDefaultCommand(new IntakeSetCmd(m_intakeChanPart2, IntakePart2Constants.defaultSpeed, IntakePart2Constants.defaultIdolSpeed));


    m_chooser.setDefaultOption("Cube + Engage", m_cubeEngage); // default
    m_chooser.addOption("Cube + Mobility", m_cubeMobility); // addition option
    m_chooser.addOption("Cone + Mobility", m_coneMobility); // addition option
    // save choice
    SmartDashboard.putData("Auto Choice", m_chooser);

    CameraServer.startAutomaticCapture();

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}..
   */
  private void configureBindings() {

    // driver controls
    new JoystickButton(JOYSTICK, 1).onTrue(new SlowDriveCmd(m_driveSubsystem, DriveConstants.slowDriveSpeed, DriveConstants.slowTurnSpeed)); // slow drive 
    new JoystickButton(JOYSTICK, 2).onTrue(new SlowDriveCmd(m_driveSubsystem, DriveConstants.kDriveSpeed, DriveConstants.kTurnSpeed)); // full speed drive 
    new JoystickButton(JOYSTICK, 3).onTrue(new TurnToSetpointCmd(m_driveSubsystem, DriveConstants.kSetpoint)); // turn to a pre-specified angle
    new JoystickButton(JOYSTICK, 4).onTrue(new AlignmentCmd(m_driveSubsystem, m_limelightSubsystem, LimelightConstants.kAlignmentSpeed)); // m_drive, m_limelight, speed
    new JoystickButton(JOYSTICK, 6).onTrue(new TurnToSetpointCmd(m_driveSubsystem, DriveConstants.kNoSetpoint)); // cancel turn
    
    // operator controls
    //new JoystickButton(JOYSTICK2, 1).onTrue(new ElevatorMidCmd(m_elevatorSubsystem)); // Move elevator to mid-level; does not work as intended
    new JoystickButton(JOYSTICK2, 2).whileTrue(new ElevatorCmd(m_elevatorSubsystem, ElevatorConstants.kSpeedDown)); // elevator descends
    new JoystickButton(JOYSTICK2, 3).onTrue(new IntakeRotationCmd(m_intakeChanPart1, IntakePart1Constants.kRotationOff, IntakePart1Constants.idol)); // set intake rotation to 0.0
    new JoystickButton(JOYSTICK2, 4).whileTrue(new ElevatorCmd(m_elevatorSubsystem, ElevatorConstants.kSpeedUp)); // elevator ascends
    new JoystickButton(JOYSTICK2, 5).whileTrue(new IntakeRotationCmd(m_intakeChanPart1, IntakePart1Constants.kRotationSpeedUp, IntakePart1Constants.idol)); // intake rotation up
    new JoystickButton(JOYSTICK2, 6).whileTrue(new IntakeSetCmd(m_intakeChanPart2, IntakePart2Constants.kCubeIn, IntakePart2Constants.idolCubeIn)); // cube intake, cone outtake
    new JoystickButton(JOYSTICK2, 7).whileTrue(new IntakeRotationCmd(m_intakeChanPart1, IntakePart1Constants.kRotationSpeedDown, IntakePart1Constants.idol)); // intake rotation down
    new JoystickButton(JOYSTICK2, 8).whileTrue(new IntakeSetCmd(m_intakeChanPart2, IntakePart2Constants.kCubeOut, IntakePart2Constants.idolCubeOut)); // cube outtake, cone intake

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    //m_driverController.b().whileTrue(m_driveSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return m_chooser.getSelected();
  
  }
}
