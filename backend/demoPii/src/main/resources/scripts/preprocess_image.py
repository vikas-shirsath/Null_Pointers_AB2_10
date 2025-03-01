import cv2
import sys
import os
import numpy as np

def preprocess_image(input_path, output_path):
    print(f"Input path: {input_path}")
    print(f"Output path: {output_path}")

    # Check if the input file exists
    if not os.path.exists(input_path):
        print(f"Error: Input file does not exist at {input_path}")
        sys.exit(1)

    # Load the image
    image = cv2.imread(input_path, cv2.IMREAD_GRAYSCALE)
    if image is None:
        print(f"Error: Unable to load image from {input_path}")
        sys.exit(1)

    # Resize the image for better OCR accuracy
    resized_image = cv2.resize(image, None, fx=2, fy=2, interpolation=cv2.INTER_LINEAR)

    # Apply adaptive thresholding
    binary_image = cv2.adaptiveThreshold(
        resized_image, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY, 11, 2
    )

    # Noise removal
    denoised_image = cv2.medianBlur(binary_image, 3)

    # Save the preprocessed image
    cv2.imwrite(output_path, denoised_image)
    print(f"Preprocessed image saved to {output_path}")

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python preprocess_image.py <input_image_path> <output_image_path>")
        sys.exit(1)

    input_path = sys.argv[1]
    output_path = sys.argv[2]
    preprocess_image(input_path, output_path)