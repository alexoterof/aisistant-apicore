# aisistant-apicore
Just a small api-gateway to interact with chatGPT api. I currently interact with this through the following shell functions

```
function gpt() {
  echo "Asking chatgpt..."
  read -r -d '' CMD_INPUT <<< "${@}"
  export query="$(printf '%s' "$CMD_INPUT" | jq -Rs .)"
  curl -s -H "Content-Type: application/json" -X POST -d "$(jq -n --arg uuid "$uuid" --arg query "$query" '{"uuid":$uuid,"query":$query}')" localhost:8080/query | \
    python3 -c "import sys, json; print(json.load(sys.stdin)['message'])"

}

function ngpt(){
  uuid=$(uuidgen)
  gpt "$@"
}
```
