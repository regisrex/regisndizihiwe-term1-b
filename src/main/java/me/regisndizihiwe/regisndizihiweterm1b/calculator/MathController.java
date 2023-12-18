package me.regisndizihiwe.regisndizihiweterm1b.calculator;

import me.regisndizihiwe.regisndizihiweterm1b.calculator.dto.CalcResponseDto;
import me.regisndizihiwe.regisndizihiweterm1b.calculator.dto.DoMathRequest;
import me.regisndizihiwe.regisndizihiweterm1b.exceptions.InvalidOperationException;
import me.regisndizihiwe.regisndizihiweterm1b.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/math-operator")
public class MathController {
    private final MathOperatorImpl mathOperator;

    @Autowired
    public MathController(MathOperatorImpl mathOperator) {
        this.mathOperator = mathOperator;
    }

    @PostMapping(path = "do-math")
    public ApiResponse doMath(@RequestBody DoMathRequest doMathRequest) throws InvalidOperationException {
        return new ApiResponse<CalcResponseDto>(200, "Op done", true, new CalcResponseDto(this.mathOperator.doMath(doMathRequest.getOperand1(), doMathRequest.getOperand2(), doMathRequest.getOperation())));
    }

}
