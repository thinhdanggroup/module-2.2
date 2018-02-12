import xxhash
import hashlib
import time
import sys

if (len(sys.argv) < 3):
    print("Arg1 : file hash")
    print("Arg2 : time to hash")
    exit(0)
# Input file name or path to file name
# Example google-chrome-stable_current_amd64.deb ~ 48.5 MB
file_name = sys.argv[1]

# Set time to benmark
time_benmark = (int)(sys.argv[2])

with open(file_name, 'rb') as f:
    contents = f.read()

# Calculate times for xxhash (non-cryptography hash)
count = 0
time_end = time.time() + time_benmark
while time.time() < time_end:
    xxhash.xxh32(contents).hexdigest()
    count += 1
print("Data is hashed : " + str(count) + " times with xxhash")

# Calculate times for md5 (cryptography hash)
count = 0
time_end = time.time() + time_benmark
while time.time() < time_end:
    hashlib.md5(contents).hexdigest()
    count += 1
print("Data is hashed : " + str(count) + " times with md5")
