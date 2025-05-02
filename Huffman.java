import java.util.*;

class HuffmanNode {
    char symbol;
    int count;
    HuffmanNode left, right;

    HuffmanNode(char symbol, int count) {
        this.symbol = symbol;
        this.count = count;
    }
}

class FrequencyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode a, HuffmanNode b) {
        return a.count - b.count;
    }
}

public class Huffman {
    static Map<Character, String> codeTable = new HashMap<>();
    static HuffmanNode root;

    static void generateCodes(HuffmanNode node, String code) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            codeTable.put(node.symbol, code);
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    static HuffmanNode buildHuffmanTree(char[] symbols, int[] frequencies) {
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>(new FrequencyComparator());
        for (int i = 0; i < symbols.length; i++) {
            minHeap.add(new HuffmanNode(symbols[i], frequencies[i]));
        }
        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();
            HuffmanNode combined = new HuffmanNode('$', left.count + right.count);
            combined.left = left;
            combined.right = right;
            minHeap.add(combined);
        }
        return minHeap.poll();
    }

    static String encodeText(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codeTable.get(c));
        }
        return sb.toString();
    }

    static String decodeText(String encoded) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                sb.append(current.symbol);
                current = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String original = "MARVIN";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : original.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }
        int n = frequencyMap.size();
        char[] symbols = new char[n];
        int[] frequencies = new int[n];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            symbols[index] = entry.getKey();
            frequencies[index] = entry.getValue();
            index++;
        }

        root = buildHuffmanTree(symbols, frequencies);
        generateCodes(root, "");

        System.out.println("Input Text: " + original);
        System.out.println("\nHuffman Code Mapping:");
        for (Map.Entry<Character, String> entry : codeTable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        String encodedData = encodeText(original);
        System.out.println("\nEncoded Data: " + encodedData);

        String decodedData = decodeText(encodedData);
        System.out.println("\nDecoded Text: " + decodedData);
    }
}
