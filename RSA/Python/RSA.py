import random
import math

def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

def generate_keypair(bits):
    p = generate_prime(bits)
    q = generate_prime(bits)
    n = p * q
    phi = (p - 1) * (q - 1)
    e = choose_public_exponent(phi)
    d = modular_inverse(e, phi)
    return ((e, n), (d, n))

def generate_prime(bits):
    while True:
        num = random.getrandbits(bits)
        if is_prime(num):
            return num

def choose_public_exponent(phi):
    e = random.randrange(2, phi)
    while math.gcd(e, phi) != 1:
        e = random.randrange(2, phi)
    return e

def modular_inverse(a, m):
    m0, x0, x1 = m, 0, 1
    while a > 1:
        q = a // m
        m, a = a % m, m
        x0, x1 = x1 - q * x0, x0
    return x1 + m0 if x1 < 0 else x1

def encrypt(public_key, plaintext):
    e, n = public_key
    ciphertext = [pow(ord(char), e, n) for char in plaintext]
    return ciphertext

def decrypt(private_key, ciphertext):
    d, n = private_key
    decrypted_text = ''.join([chr(pow(char, d, n)) for char in ciphertext])
    return decrypted_text

# Example usage:
bits = 256
public_key, private_key = generate_keypair(bits)

message = "Hello, RSA!"
print("Original message:", message)

encrypted_message = encrypt(public_key, message)
print("Encrypted message:", encrypted_message)

decrypted_message = decrypt(private_key, encrypted_message)
print("Decrypted message:", decrypted_message)
