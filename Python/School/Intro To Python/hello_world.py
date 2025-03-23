# I program mainly in JetBrain's Pycharm. Can I turn in .py files instead of dealing with the headache that is CodeHS?
import re

target = "A7F3K9X2P4G2M8Z1Q9W1R6T5B3N7L2C8X5Y2V9J3T8Z4K6P1M2Q3N9W7R1F5C8X6K4Z2P3Y7W9T6M2F3L8X2N5Q4K9V7B1T3C6Z8R2W5M4Y9N6P3F7L1Q8Z2K5C9X7R3M6T2V8B4W1F9N7Y3Z6K2C8P5Q9T4R8WHELLO7X2L3M5B9F6Y2T8C1Z7P4Q3V6N9K8W5R1Y7L2F9C3X6T5M8B4N2Z9K7Q5W3P8R6Y1T9C2L7V4WORLDX5M9F3K8"

r = r"(H\w{3}O)|(W\w{2}LD)"

hits = re.findall(r, target)

out = ""

for hit in hits:
    out += hit[0].title() + hit[1].title() + " "

print(out)
