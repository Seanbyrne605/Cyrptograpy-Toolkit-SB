from hashlib import sha256

# Function to compute Merkle root from a leaf node and its Merkle branch
def compute_merkle_root(leaf, branch, index):
    current_hash = leaf
    for node in branch:
        # Determine the order of concatenation based on the index
        if index % 2 == 0:
            current_hash = sha256((current_hash + node).encode()).hexdigest()
        else:
            current_hash = sha256((node + current_hash).encode()).hexdigest()
        # Move to the next level in the tree
        index = index // 2
    return current_hash

# Compute SHA-256 value of the string 'd'
str_value = "d"
sha256_value = sha256(str_value.encode()).hexdigest()

# Leaf node at index 19 in the Merkle tree
leaf_node = sha256_value
merkle_branch = [
    "2e7d2c03a9507ae265ecf5b5356885a53393a2029d241394997265a1a25aefc6",
    "cf716f1e7934014cc67d3bded144f2b5abac20369b70f1c3a8eb3276d9de209a",
    "9376e34d9a15765d98c8e5fd68d5e3b18b20116b9d4faa293f326a93731078ef",
    "2e15ecd6e7d77f95c9275a7cc4cf9338518ef7b9490097510f994d8111ccf1d8",
    "8a4528a3fe72db85253e6b2d0dce3de5ede91ece01ac45a4b86b29f0681e6fcc"
]

# Compute the Merkle root
merkle_root = compute_merkle_root(leaf_node, merkle_branch, 2)

# Display the result in the specified format
result = f"4hex1: {sha256_value[:4]}, 4hex2: {merkle_root[:4]}"
print(result)