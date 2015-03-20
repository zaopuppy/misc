function gen_random_id () {
  var id; 
  id = Math.random().toString(36).substring(7);
  return id;
}

console.log(gen_random_id());

