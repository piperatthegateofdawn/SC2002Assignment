import java.util.Random;


public class TwoFactorAuth {
    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_TIME = 5 * 60 * 1000; // 5 minutes in milliseconds
    private String generatedOtp;
    public String getGeneratedOtp() {
        return generatedOtp;
    }

    private long otpGeneratedTime;

    public void generateOtp() {
        Random random = new Random();
        generatedOtp = String.format("%06d", random.nextInt(1000000));
        otpGeneratedTime = System.currentTimeMillis();
        System.out.println("Your OTP is: " + generatedOtp + " (Valid for 5 minutes)");
    }

    public boolean verifyOtp(String userInput) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - otpGeneratedTime > OTP_EXPIRY_TIME) {
            System.out.println("OTP has expired. Please generate a new one.");
            return false;
        }
        if (generatedOtp.equals(userInput)) {
            System.out.println("OTP verified successfully!");
            return true;
        } else {
            System.out.println("Invalid OTP. Try again.");
            return false;
        }
    }
}
