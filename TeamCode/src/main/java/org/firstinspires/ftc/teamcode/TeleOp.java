package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "4785")
public class TeleOp extends OpMode
{
    DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
    DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
    DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
    DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
    DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
    @Override
    public void init()
    {
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = 1.1 * gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        frontLeftMotor.setPower((y + x + rx) / denominator);
        backLeftMotor.setPower((y - x + rx) / denominator);
        frontRightMotor.setPower((y - x - rx) / denominator);
        backRightMotor.setPower((y + x - rx) / denominator);
        if (gamepad1.dpad_down) {
            armMotor.setPower(-1.0);
        }
        else if (gamepad1.dpad_up) {
            armMotor.setPower(1.0);
        } else if (gamepad1.dpad_right) {
            armMotor.setPower(-1.0);
            armMotor.setTargetPosition(0);
        } else if (gamepad1.dpad_left) {
            armMotor.setPower(1.0);
            armMotor.setTargetPosition(0);
        }
    }
}
