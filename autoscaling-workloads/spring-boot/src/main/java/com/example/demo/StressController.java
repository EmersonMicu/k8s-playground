package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stress")
public class StressController {

    @GetMapping("/cpu")
    public String stressCpu(@RequestParam int duration) {
        long endTime = System.currentTimeMillis() + (duration * 1000);
        while (System.currentTimeMillis() < endTime) {
            isPrime(10_000_000);
        }
        return "CPU stress test completed for " + duration + " seconds.";
    }

    @GetMapping("/memory")
    public String stressMemory(@RequestParam int sizeInMb) {
        List<byte[]> memoryHog = new ArrayList<>();
        try {
            for (int i = 0; i < sizeInMb; i++) {
                memoryHog.add(new byte[1024 * 1024]); // 1MB chunks
            }
        } catch (OutOfMemoryError e) {
            return "OutOfMemoryError: Could only allocate " + memoryHog.size() + " MB.";
        }
        return "Memory stress test completed. Allocated " + sizeInMb + " MB.";
    }

    @GetMapping("/cleanMemory")
    public String cleanMemory() {
        System.gc();
        return "Garbage Collection requested)";
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
