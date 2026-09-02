# Bit Manipulation

## 📌 Overview
Bit Manipulation operates on individual binary bits of integer numbers directly, providing extremely fast operations and low memory usage.

## 🛠️ Common Patterns & Techniques
- Bitwise Operators: `&`, `|`, `^`, `~`, `<<`, `>>`
- Setting, clearing, toggling, and checking specific bits
- XOR properties (e.g., `x ^ x = 0`, `x ^ 0 = x`, Single Number problem)
- Brian Kernighan's Algorithm (Counting set bits via `n & (n - 1)`)
- Bitmasking for subset generation

## 📑 Practice Problems
| Problem | Difficulty | Solution | Notes |
|---|---|---|---|
| Single Number | Easy | [136_Single_Number.java](./136_Single_Number.java) | Bitwise XOR Cancellation |
