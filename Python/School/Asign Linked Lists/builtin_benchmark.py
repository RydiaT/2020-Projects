import time
import csv
from sys import getsizeof

def benchmark_add_time():
    results = []
    i = 0
    skipped_iterations = 0
    while True:
        n = 2 ** i
        # Skip initial iterations where time is negligible
        if skipped_iterations < 10 and n < 512:
            skipped_iterations += 1
            i += 1
            continue
        # Stop if memory limit exceeded or time exceeds 5 minutes
        # if n * getsizeof(int()) > 2 ** 100:
        #     break
        linked_list = []
        start_time = time.time()  # Initialize start_time for each loop
        for j in range(n):
            linked_list.append(j)
        end_time = time.time()
        elapsed_time = end_time - start_time

        if elapsed_time > 300:
            break
        results.append((n, elapsed_time))
        i += 1
    return results


results = benchmark_add_time()
with open('Data/output2.csv', mode='w', newline='') as file:  # 'w' mode to overwrite existing file
    writer = csv.writer(file)
    writer.writerow(['n', 'Time (seconds)'])
    for result in results:
        writer.writerow(result)
