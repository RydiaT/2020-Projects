from PIL import Image
import numpy as np
from random import randint
from time import time


def rand_pixel():
    return randint(0, 255), randint(0, 255), randint(0, 255)


def average_color(region):
    return np.mean(region, axis=(0, 1)).astype(int)


def downscale_image(img_array, scale_factor):
    height, width, _ = img_array.shape
    new_height = height // scale_factor
    new_width = width // scale_factor

    new_img_array = np.zeros((new_height, new_width, 3), dtype=int)

    for y in range(new_height):
        for x in range(new_width):
            region = img_array[y * scale_factor:(y + 1) * scale_factor, x * scale_factor:(x + 1) * scale_factor]
            new_img_array[y, x] = average_color(region)

    return Image.fromarray(new_img_array.astype('uint8'))


def pixelate_image(img, scale_factor):
    """Pixelate the image by averaging colors in sections."""
    img_array = np.array(img)
    height, width, _ = img_array.shape

    # Calculate the size of each section
    section_height = height // scale_factor
    section_width = width // scale_factor

    # Create a copy of the original image to modify
    pixelated_img_array = img_array.copy()

    for y in range(scale_factor):
        for x in range(scale_factor):
            # Calculate the region bounds
            y_start = y * section_height
            y_end = (y + 1) * section_height
            x_start = x * section_width
            x_end = (x + 1) * section_width

            # Handle edge cases where the section might not perfectly fit the image dimensions
            if y == scale_factor - 1:
                y_end = height
            if x == scale_factor - 1:
                x_end = width

            # Extract the region and calculate the average color
            region = img_array[y_start:y_end, x_start:x_end]
            avg_color = average_color(region)

            # Fill the section with the average color
            pixelated_img_array[y_start:y_end, x_start:x_end] = avg_color

    # Convert the numpy array back to an image
    pixelated_img = Image.fromarray(pixelated_img_array.astype('uint8'))
    return pixelated_img


start_time = time()

original = Image.open('yaoi.png')
array = np.array(original)

changed = pixelate_image(array, 50)
changed.save('yaoiTEST.png')

end_time = time() - start_time

print("Finished in: " + str(end_time) + " seconds.")

changed.show()
