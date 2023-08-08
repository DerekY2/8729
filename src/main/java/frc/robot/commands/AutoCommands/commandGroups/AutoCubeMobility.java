// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;

import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.ElevatorCmd;
import frc.robot.commands.IntakeRotationCmd;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.subsystems.IntakeChanPart2;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.CubeMobility;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoCubeMobility extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoCubeMobility(DriveSubsystem m_driveSubsystem, ElevatorSubsystem m_elevatorSubsystem, IntakeChanPart1 m_intakeChanPart1, IntakeChanPart2 m_intakeChanPart2) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);

    addCommands(

      // shoot cube, then exit community zone
      new IntakeRotationCmd(m_intakeChanPart1, CubeMobility.kRotationDownSpeed, AutoConstants.kRotationStall).withTimeout(CubeMobility.kRotationDownTimeout),
      new ElevatorCmd(m_elevatorSubsystem, CubeMobility.kElevatorUpSpeed),
      new ElevatorCmd(m_elevatorSubsystem, CubeMobility.kElevatorCorrectionSpeed).withTimeout(CubeMobility.kElevatorCorrectionTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, CubeMobility.kRotationUpSpeed, AutoConstants.kRotationStall).withTimeout(CubeMobility.kRotationUpTimeout),
      new IntakeSetCmd(m_intakeChanPart2, CubeMobility.kOuttakeSpeed, AutoConstants.kIntakeStall).withTimeout(CubeMobility.kOuttakeTimeout),
      new IntakeRotationCmd(m_intakeChanPart1, CubeMobility.kRotationResetSpeed, AutoConstants.kRotationStall).withTimeout(CubeMobility.kRotationResetTimeout),
      new ElevatorCmd(m_elevatorSubsystem, CubeMobility.kElevatorDownSpeed).withTimeout(CubeMobility.kElevatorDownTimeout),
      new AutoDrive(m_driveSubsystem, CubeMobility.kDriveSpeed, CubeMobility.kTurnSpeed).withTimeout(CubeMobility.kDriveTimeout)

    );
  }
}

