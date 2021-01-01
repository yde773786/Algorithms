import sys
import document_distance as doc_dist


def main():
    for arg in sys.argv[1:]:
        if arg == '--doc-dist':
            doc_dist.main()


if __name__ == "__main__":
    main()
