// https://www.hackerrank.com/challenges/non-divisible-subset/problem
function nonDivisibleSubset(k, s) {
    let mod = new Array(k);
    let result = 0;
    mod.fill(0);
    
    // count input mod k
    for(var i in s) {
        mod[s[i]%k]++;
    }
    
    // mod[0], mod[k/2] : count as 1
    if(mod[0]>0) result++;
    if(k%2===0&&mod[k/2]>0) result++;

    // count max(mod[i], mod[k-i])
    for(var i=1; i<k/2;i++) {
        result+=Math.max(mod[i],mod[k-i]);
    }
    return result;
}

const k = 3;
const s = [1, 7, 2, 4];
const result = nonDivisibleSubset(k, s);

console.log(result + '\n');