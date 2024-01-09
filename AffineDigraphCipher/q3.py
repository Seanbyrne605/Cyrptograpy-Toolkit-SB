def mod_inverse(a, m):
    """Find the modular inverse of a under modulo m"""
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    return None

def affine_decrypt(cipher, a, b, alphabet):
    """Decrypt the ciphertext using the Affine cipher"""
    plain = ''
    for char in cipher:
        if char in alphabet:
            idx = alphabet.index(char)
            plain += alphabet[(mod_inverse(a, len(alphabet)) * (idx - b)) % len(alphabet)]
        else:
            plain += char
    return plain

# Alphabet including lowercase letters and special symbols
alphabet = 'abcdefghijklmnopqrstuvwxyz!£$%^&*_+-='

# Given (plaintext, ciphertext) pair
plain_pair = '&*^*&'
cipher_pair = 'e£s£e'

# Find a and b
# For each letter in the pair, find a and b such that a * x + b = y mod m
# Since we have two pairs, we can solve the equations to find a and b
m = len(alphabet)
a, b = None, None
for a_try in range(m):
    for b_try in range(m):
        if all(alphabet[(a_try * alphabet.index(plain_char) + b_try) % m] == cipher_char for plain_char, cipher_char in zip(plain_pair, cipher_pair)):
            a, b = a_try, b_try
            break
    if a is not None:
        break

# Decrypt the given ciphertext
ciphertext = "l^bb^bb^gg^"
plaintext = affine_decrypt(ciphertext, a, b, alphabet)

# Find the number equivalent of '&'
number_equiv = alphabet.index('&')

# Output
print(f"Number equivalent: {number_equiv}, a: {a}, b: {b}, Plaintext: {plaintext}")
