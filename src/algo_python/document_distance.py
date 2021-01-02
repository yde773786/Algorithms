"""
MIT 6.006 Introduction to Algorithms, Fall 2011;
Coverage of Document distance
(Lecture 2)
"""
from typing import List, Dict
from math import acos, sqrt


def main():
    input_path = input('Enter path to first document:\n')
    with open(input_path, 'r') as file:
        doc_1 = file.read()

    input_path = input('Enter path to second document:\n')
    with open(input_path, 'r') as file:
        doc_2 = file.read()

    print(document_distance(doc_1.split(), doc_2.split()))


def freq_finder(word_list: List[str]) -> Dict[str, int]:
    word_table = {}
    for word in word_list:
        if word in word_table:
            word_table[word] += 1
        else:
            word_table[word] = 1

    return word_table


def document_distance(doc_1_split: List[str], doc_2_split: List[str]) -> float:
    """Finds the document distance between two provided documents. First splits
    each document into words with whitespace as delimiter. Creates two dictionaries
    mapping word to frequency for each document.

    Having created two dictionaries, find out which one is the smallest to decrease
    number of iterations. Then loop through and find dot product of the two vectors
    (word -> frequency). Divide by product of length of the two vectors.

    :param doc_1_split: List representing split first document.
    :param doc_2_split: List representing split second document.
    :return: Required document distance
    """
    doc_1_dict = freq_finder(doc_1_split)
    doc_2_dict = freq_finder(doc_2_split)

    min_dict = doc_1_dict
    max_dict = doc_2_dict
    if len(doc_1_dict) > len(doc_2_dict):
        min_dict = doc_2_dict
        max_dict = doc_1_dict

    dot_prod = 0
    for word in min_dict:
        if word in max_dict:
            dot_prod += min_dict[word] * max_dict[word]

    doc_1_sum = sqrt(sum((doc_1_dict[wrd]) ** 2 for wrd in doc_1_dict))
    doc_2_sum = sqrt(sum((doc_2_dict[wrd]) ** 2 for wrd in doc_2_dict))

    return acos(dot_prod / (doc_1_sum * doc_2_sum))


if __name__ == '__main__':
    main()
