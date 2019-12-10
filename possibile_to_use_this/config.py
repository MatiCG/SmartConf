nice_words = ["point suivant", "ordre",
              "necessaire", "important", "crucial"]

def begin_synthesis(text, keywords):
    i = 0
    text2 = text.lower()
    tab = keywords + nice_words
    while i < len(tab):
        text2 = text2.replace(tab[i], tab[i].upper())
        i = i + 1
    print(text2)
    return text2