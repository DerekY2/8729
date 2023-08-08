// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;

import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.ElevatorCmd;
import frc.robot.commands.IntakeRotationCmd;
import frc.robot.commands.EncoderDrive;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.subsystems.IntakeChanPart2;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ConeMobility;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoConeMobility extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoConeMobility(DriveSubsystem m_driveSubsystem, ElevatorSubsystem m_elevatorSubsystem, IntakeChanPart1 m_intakeChanPart1, IntakeChanPart2 m_intakeChanPart2) {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);

    addCommands(
        
      // shoot cone, then exit community zone
      new IntakeRotationCmd(m_intakeChanPart1, ConeMobility.kRotationUpSpeed, AutoConstants.kRotationStall).withTimeout(ConeMobility.kRotationUpTimeout),
      new ElevatorCmd(m_elevatorSubsystem, ConeMobility.kElevatorUpSpeed),
      new ElevatorCmd(m_elevatorSubsystem, ConeMobility.kElevatorCorrectionSpeed).withTimeout(ConeMobility.kElevatorCorrectionTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, ConeMobility.kRotationDownSpeed, AutoConstants.kRotationStall).withTimeout(ConeMobility.kRotationDownTimeout),
      new IntakeSetCmd(m_intakeChanPart2, ConeMobility.kOuttakeSpeed, AutoConstants.kIntakeStall).withTimeout(ConeMobility.kOuttakeTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, ConeMobility.kRotationResetSpeed, AutoConstants.kRotationStall).withTimeout(ConeMobility.kRotationResetTimeout),
      new ElevatorCmd(m_elevatorSubsystem, ConeMobility.kElevatorDownSpeed).withTimeout(ConeMobility.kElevatorDownTimeout),
      new AutoDrive(m_driveSubsystem, ConeMobility.kDriveSpeed, ConeMobility.kTurnSpeed).withTimeout(ConeMobility.kDriveTimeout),
      new EncoderDrive(m_driveSubsystem, ConeMobility.kEncoderDriveSpeed, ConeMobility.kEncoderTurnSpeed, ConeMobility.kEncoderDriveDistance, ConeMobility.kEncoderEndInverted)

    );
  }
}

