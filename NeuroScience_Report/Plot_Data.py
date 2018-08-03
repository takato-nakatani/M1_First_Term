
import matplotlib.pyplot as plt

def PlotData(LSE_n):
    ax = plt.subplot()
    ax.grid(which="both")
    ax.set_xlabel("Number of learning", fontsize=15)
    ax.set_ylabel("LSE-Value", fontsize=15)

    ax.set_xlim([0, 300])
    ax.set_ylim([4, 10])
    plt.plot(LSE_n)
    plt.show()

    plt.rcParams["font.size"] = 15
    plt.xlabel("Number of learning", fontsize=15)
    plt.ylabel("LSE-Value", fontsize=15)
    plt.plot(LSE_n)
    plt.show()