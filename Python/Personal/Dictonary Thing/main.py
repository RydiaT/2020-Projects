from Dict import generate_haiku, generate_basic_essay

essay_content = generate_basic_essay(750)

with open("output.txt", "w") as file:
    file.write(essay_content)