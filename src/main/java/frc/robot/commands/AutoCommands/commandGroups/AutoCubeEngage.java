// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;

import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.ElevatorCmd;
import frc.robot.commands.EncoderDrive;
import frc.robot.commands.IntakeRotationCmd;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.subsystems.IntakeChanPart2;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.CubeEngage;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoCubeEngage extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoCubeEngage(DriveSubsystem m_driveSubsystem, ElevatorSubsystem m_elevatorSubsystem, IntakeChanPart1 m_intakeChanPart1, IntakeChanPart2 m_intakeChanPart2) {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);

    addCommands(
        
        // shoot cube, then engage
      new IntakeRotationCmd(m_intakeChanPart1, CubeEngage.kRotationUpSpeed, AutoConstants.kRotationStall).withTimeout(CubeEngage.kRotationUpTimeout),
      new ElevatorCmd(m_elevatorSubsystem, CubeEngage.kElevatorUpSpeed),
      new ElevatorCmd(m_elevatorSubsystem, CubeEngage.kElevatorCorrectionSpeed).withTimeout(CubeEngage.kElevatorCorrectionTimeout),
      new AutoDrive(m_driveSubsystem, CubeEngage.kDriveSpeed, CubeEngage.kTurnSpeed).withTimeout(CubeEngage.kDriveTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, CubeEngage.kRotationAlignSpeed, AutoConstants.kRotationStall).withTimeout(CubeEngage.kRotationAlignTimeout),
      new IntakeSetCmd(m_intakeChanPart2, CubeEngage.kOuttakeSpeed, AutoConstants.kIntakeStall).withTimeout(CubeEngage.kOuttakeTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, 0.6, AutoConstants.kRotationStall).withTimeout(0.5),
      new ElevatorCmd(m_elevatorSubsystem, 0.7).withTimeout(1.4),
      
      //new AutoEngage(m_driveSubsystem)
      new AutoDrive(m_driveSubsystem, 0.6, 0.0).withTimeout(3.0), 
      new EncoderDrive(m_driveSubsystem, 0.55, 0.0, 2800, false),
      new EncoderDrive(m_driveSubsystem, 0.0, 0.4, 430, false)

    );
  }
}

