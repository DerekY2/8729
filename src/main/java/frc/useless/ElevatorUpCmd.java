// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.useless;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.ElevatorConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ElevatorUpCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_elevatorSubsystem;
  private boolean  maxHeight;
  private boolean goingUp;
  private final double upSpeed = ElevatorConstants.kSpeedUp;
  private final double correctionSpeed = ElevatorConstants.kCorrectionSpeed;

  /**
   * Creates a new ElevatorMoveCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorUpCmd(ElevatorSubsystem subsystem) {
    m_elevatorSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ElevatorMoveCmd Started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    maxHeight = m_elevatorSubsystem.getTopLimitSwitch();
    goingUp = m_elevatorSubsystem.getUp();

    if(!goingUp){ // if elevator is not "ascending"
      if (maxHeight) {  // check if limit switch reached; if so, bring elevator down a bit
        m_elevatorSubsystem.resetTimer();
        m_elevatorSubsystem.startTimer();
        if (m_elevatorSubsystem.getTimer() < 0.3) {
          m_elevatorSubsystem.setMotor(correctionSpeed);
        }
        System.out.println("Yes");
        m_elevatorSubsystem.setUp(false);
      }
      else { // if limit switch not reached(not at max height), bring elevator up and set "up" to true
        m_elevatorSubsystem.setMotor(upSpeed);
        System.out.println("no");
        m_elevatorSubsystem.setUp(true);
      }
    }
    if (goingUp) { //if up== true limit switch true, turn off elevator and let slide little
      if (maxHeight) { // if elevator is going up AND maxHeight is true(reached max height) the elevator will automatically descend.
        m_elevatorSubsystem.setMotor(0.0);
        m_elevatorSubsystem.resetTimer();
        m_elevatorSubsystem.startTimer();
        if (m_elevatorSubsystem.getTimer() < 0.3) { // go down for a little
          m_elevatorSubsystem.setMotor(correctionSpeed);
        }
        m_elevatorSubsystem.setUp(false); // once descended, button 4 works again.
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.setMotor(0.0);
    m_elevatorSubsystem.setUp(false);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
